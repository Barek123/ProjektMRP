(function () {
    'use strict';

    angular
        .module('frontend')
        .controller('PurchasesController', PurchasesController);

    /** @ngInject */
    function PurchasesController(ProductsFactory, $cookies, $location, toastr) {
        var vm = this;

        vm.go = go;

        function go(path) {
            $location.path(path);
        }
    }
})();
