(function () {
    'use strict';

    angular
        .module('frontend')
        .controller('ProductsController', ProductsController);

    /** @ngInject */
    function ProductsController(ProductsFactory, $cookies, $location, toastr, $uibModal) {
        var vm = this;
        vm.products = [];
        vm.measures = [];
        vm.productToSend = {};
        vm.editMode = false;

        vm.go = go;
        vm.cancel = cancel;
        vm.modifyProduct = modifyProduct;
        vm.saveProduct = insertProduct;
        vm.deleteProduct = deleteProduct;


        function modifyProduct(product){
            vm.productToSend = product;
            vm.editMode = true;
        }

        function cancel(){
            vm.editMode = false;
            vm.productToSend = {};
        }

        function insertProduct(){
            if(vm.editMode){
                ProductsFactory.edit({id: vm.productToSend.id}, vm.productToSend, function(data) {
                    vm.editMode = false;
                    var productIndex = vm.products.map(function(product) {
                        return product.id;
                    }).indexOf(data.id);
                    vm.products[productIndex] = vm.productToSend;
                    vm.productToSend = {};
                    toastr.success("Produkt poprawnie zmodyfikowany");
                }, function(error) {
                    toastr.error("Błąd podczas modyfikacji produktu");
                })
            }else{
                ProductsFactory.insert({}, vm.productToSend, function(data) {
                    vm.products.push(data);
                    toastr.success("Produkt dodany poprawnie");
                    vm.productToSend={};
                }, function(error) {
                    toastr.error("Błąd podczas dodawania produktu");
                })
            }
        }

        function go(path) {
            $location.path(path);
        }

        function getAllProducts() {
            ProductsFactory.getAll({}, {}, function (data) {
                vm.products = data.map(function(product) {
                    var newProduct = product;
                    newProduct.storage = product.storage[0].value;
                    return newProduct;
                });
            }, function(error) {
                toastr.error("Błąd podczas pobieranie produktów");
            });
        };

        function deleteProduct(product) {
            var deleteProduct = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'app/components/dialogs/delete-product.dialog.html',
                controller: 'deleteProductDialog',
                controllerAs: 'modal',
                size: 'md',
                resolve: {
                    title: function () {
                        return 'Usuń produkt';
                    },
                    description: function() {
                        return 'Czy na pewno chcesz usunąć produkt \"'+product.productName+'\" ?'
                    }
                }
            }).result.then(function(result) {
                if(result){
                    ProductsFactory.delete({id: product.id}, {}, function(){
                        toastr.success("Produkt usunięty");
                        var productIndex = vm.products.map(function(item) {
                            return item.id;
                        }).indexOf(product.id);
                        vm.products.splice(productIndex, 1);
                    }, function(error){
                        toastr.error("Błąd podczas usuwania produktu");
                    })
                }
            });
        }

        function getMeasures() {
            ProductsFactory.getMeasures({}, {}, function (data) {
                vm.measures = data;
            }, function(error) {
                toastr.error("Błąd podczas pobieranie jednostek");
            });
        }

        getAllProducts();
        getMeasures();
    }
})();
