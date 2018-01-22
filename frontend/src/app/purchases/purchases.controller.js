(function () {
    'use strict';

    angular
        .module('frontend')
        .controller('PurchasesController', PurchasesController);

    /** @ngInject */
    function PurchasesController(ProductsFactory, MrpFactory, PurchaseFactory, $cookies, $location, toastr, $moment) {
        var vm = this;

        vm.productCount;
        vm.deadlineDate;
        vm.productToSend;
        vm.products = [];
        vm.purchases = [];
        vm.filteredPurchases = [];

        vm.currentRange = {};
        vm.dateValueToShow = '';

        vm.go = go;
        vm.addPurchase = addPurchase;
        vm.changeDate = changeDate;
        vm.getValueByDay = getValueByDay;



        function go(path) {
            $location.path(path);
        }

        function addPurchase() {
            if (!(vm.productCount && vm.deadlineDate && vm.productToSend)) {
                toastr.warning('Nie ma wypełnionych wartości do złożenia nowego zamówienia');
                return;
            }

            MrpFactory.insert({}, {
                date: vm.deadlineDate,
                productId: vm.productToSend.id,
                number: vm.productCount
            }, function (data) {
                toastr.success('Zlecenie zostało dodane');
                vm.deadlineDate = vm.productToSend = vm.productCount = undefined;
                init();
            }, function (error) {
                toastr.error('Zadanie nie może zostać zrealizowane, zmień datę na późniejszą');
            })
        }

        function init() {
            getAllProducts();
            getAllPruchases();
        }

        function changeDate(type, next) {
            var typeRaw = type;
            var value = 1;
            if (type == 'week') {
                typeRaw = 'days';
                value = 7;
            }

            if (next) {
                vm.currentRange.dateFrom = $moment(vm.currentRange.dateFrom).add(value, typeRaw)
                vm.currentRange.dateTo = $moment(vm.currentRange.dateTo).add(value, typeRaw)
            } else {
                vm.currentRange.dateFrom = $moment(vm.currentRange.dateFrom).subtract(value, typeRaw)
                vm.currentRange.dateTo = $moment(vm.currentRange.dateTo).subtract(value, typeRaw)
            }
            updateDateValueToShow();
            filterPurchasesToTable();
        }

        function updateDateValueToShow() {
            vm.dateValueToShow = $moment(vm.currentRange.dateFrom).format('YYYY/MM/DD') + ' - ' + $moment(vm.currentRange.dateTo).format('YYYY/MM/DD');
            vm.currentRange.dateFromRaw = $moment(vm.currentRange.dateFrom).valueOf();
            vm.currentRange.dateToRaw = $moment(vm.currentRange.dateTo).valueOf();
        }

        function filterPurchasesToTable() {
            vm.filteredPurchases = [];
            var dayItems = [0,0,0,0,0,0,0];
            var mappedPurchases = vm.purchases.filter(function (purchase) {
                return (purchase.endDate > vm.currentRange.dateFromRaw && purchase.endDate < vm.currentRange.dateToRaw);
            }).map(function(purchase){
                var dayInWeek = $moment(purchase.endDate).day();
                purchase.position = dayItems[dayInWeek];
                purchase.day = dayInWeek;
                dayItems[dayInWeek] = dayItems[dayInWeek] + 1; 
                return purchase;
            });

            var finishItems = false;
            var index = 0;
            do{
                finishItems = true;
                var valueToPush  ={index: index, list: mappedPurchases.filter(function(data){
                    if(data.position == index){
                        finishItems = false;
                    }
                    return data.position == index;
                })};
                if(!finishItems){
                    vm.filteredPurchases.push(valueToPush);
                }

                index ++;
            }while(!finishItems)
        }

        function getValueByDay(position, day){
            if(vm.filteredPurchases.length == 0){
                return;
            }
            var resultList = vm.filteredPurchases.filter(function(data){
                return data.index == position;
            });
            if(!resultList || resultList.length==0){
                return;
            }
            resultList = resultList[0].list.filter(function(data){
                return data.day == day;
            });

            if(resultList.length>0){
                return resultList[0].product.productName+' ('+resultList[0].brutto+' '+resultList[0].product.productUnit+'.)';
            }
            return '';
        }

        function getByRow(row){
            return vm.filteredPurchases.filter(function(data){
                return data.position = row;
            });
        }

        function getAllPruchases() {
            PurchaseFactory.getAll({}, {}, function (data) {
                vm.purchases = data;

                vm.currentRange.dateFrom = $moment();
                if($moment(vm.currentRange.dateFrom).day()!=1){
                    vm.currentRange.dateFrom = $moment(vm.currentRange.dateFrom).subtract($moment(vm.currentRange.dateFrom).day(), 'days')
                }
                vm.currentRange.dateTo = $moment(vm.currentRange.dateFrom).add(7, 'days');
                updateDateValueToShow();
                filterPurchasesToTable();
            }, function (error) {
                toastr.error('Nie udało się pobrać listy zamówień');
            });
        }

        function getAllProducts() {
            ProductsFactory.getAll({}, {}, function (data) {
                vm.products = data.map(function (product) {
                    var newProduct = product;
                    newProduct.storage = product.storage[0].value;
                    return newProduct;
                });
            }, function (error) {
                toastr.error("Błąd podczas pobieranie produktów");
            });
        };

        init();
    }
})();
