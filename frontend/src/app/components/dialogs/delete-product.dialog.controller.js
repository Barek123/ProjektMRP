(function () {
    'use strict';
  
    angular
      .module('frontend')
      .controller('deleteProductDialog', deleteProductDialog);
  
    /** @ngInject */
    function deleteProductDialog($uibModalInstance, title, description) {
      var vm = this;

      vm.title = title;
      vm.description = description;
      vm.ok = ok;
      vm.cancel = cancel;
      
      function ok() {
        $uibModalInstance.close(true);
      }
  
      function cancel() {
        $uibModalInstance.close(false);
      }
    }
  })();
  