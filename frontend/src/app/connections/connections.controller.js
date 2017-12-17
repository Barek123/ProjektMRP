(function () {
    'use strict';

    angular
        .module('frontend')
        .controller('ConnectionsController', ConnectionsController);

    /** @ngInject */
    function ConnectionsController(ProductsFactory, ProductsAssociationFactory, $cookies, $location, toastr) {
        var vm = this;
        vm.products = [];
        vm.productsExceptOne = [];
        vm.connections = undefined;
        vm.selectedProductId = undefined;
        vm.addedProduct = {};
        vm.productQuery = '';
        vm.numberProductToAdd = undefined;

        vm.go = go;
        vm.productClick = productClick;
        vm.addProductToAssociation = addProductToAssociation;
        vm.addConnection = addConnection;
        vm.getConnections = getConnections;

        function go(path) {
            $location.path(path);
        }

        function getAllProducts() {
            ProductsFactory.getAll({}, {}, function (data) {
                vm.products = data;
                if(vm.products.length>0){
                    productClick(vm.products[0].id);
                }
            }, function(error) {
                toastr.error("Błąd podczas pobieranie produktów");
            });
        };

        function productClick(id){
            vm.selectedProductId = id;
            getConnections();
            getConnectionsWithoutMe();
            vm.addedProduct = {};
        }

        function addProductToAssociation(product){
            vm.addedProduct = product;
            vm.numberProductToAdd = undefined;
        }

        function getConnections(){
            if(vm.selectedProductId===undefined){
                return;
            }
            ProductsAssociationFactory.getObject({id: vm.selectedProductId, sPart: 'tree'}, {}, function(data){
                vm.connections = data;
            });
        }
        
        function addConnection(){
            var filteredList = vm.products.filter(function(item) {
                return item.id == vm.selectedProductId
            });
            if(filteredList.length > 0){
                ProductsAssociationFactory.insert({}, {productParent: filteredList[0], product: vm.addedProduct, number: vm.numberProductToAdd}, function(data) {

                    toastr.success("Poprawnie dodano powiązanie");

                    ProductsAssociationFactory.getObject({id: vm.addedProduct.id, sPart: 'tree', tPart: vm.numberProductToAdd}, {}, function(data){
                        if(vm.connections.products.filter(function(item) { 
                            return item.parentId == data.parentId
                        }).length > 0) {
                            var index = vm.connections.products.map(function(item) { 
                                return item.parentId
                            }).indexOf(data.parentId);
                            vm.connections.products[index].number += vm.numberProductToAdd;
                        }else{
                            data.level = 2;
                            vm.connections.products.push(data);
                        }
                        
                        vm.addedProduct = {};
                    });

                }, function(error){
                    if(error.status == 409){
                        toastr.error("Nie można dodać tego produktu ponieważ produkt nadrzędny znajduje się już w dodawanym produkcie");
                    }else{
                        toastr.error("Błąd dodawania");
                    }
                })
            }
        }

        function getConnectionsWithoutMe(){
            if(vm.selectedProductId===undefined){
                return;
            }
            ProductsFactory.expectOne({id: vm.selectedProductId}, {}, function(data){
                vm.productsExceptOne = data;
            });
        }

        getAllProducts();
    }
})();
