/**
 * Created by pwiacek on 8/25/2016.
 */
(function () {
  angular
    .module('frontend')
    .directive("connectionDirective", connectionDirective)

  function connectionDirective() {
    return {
      restrict: 'EA',
      templateUrl: 'app/connections/connectiondirective.html',
      scope: {
        connections: '=connections',
        main: '=main',
        callback: '&callback'
      },
      controller: ConnectionDirective,
      controllerAs: 'vm'
    }
  }

  function ConnectionDirective($scope, $location, toastr, $uibModal, ProductsAssociationFactory) {
    var vm = this;
    vm.showChildren = false;

    vm.getIcon = getIcon;
    vm.deleteProduct = deleteProduct;

    $scope.$watch('connections', function () {
      vm.connections = $scope.connections;
      if(vm.main==undefined && vm.connections.level == 1){
        vm.main = vm.connections;
      }
    })
    $scope.$watch('main', function () {
      vm.main = $scope.main;
    })

    function getIcon() {
      if (vm.connections.products == undefined || vm.connections.products.length == 0) {
        return '';
      }
      return (!vm.showChildren) ? 'fa fa-chevron-right' : 'fa fa-chevron-down';
    }

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
          description: function () {
            return 'Czy na pewno chcesz usunąć połączenie \"'+ vm.main.parentname +'\" i \"' + vm.connections.parentname+ '\" ?'
          }
        }
      }).result.then(function (result) {
        if (result) {
          ProductsAssociationFactory.delete({id: vm.main.parentId, childId: vm.connections.parentId}, {}, function(data){
            toastr.success("Poprawnie usunięto powiązanie");
            $scope.callback();
          }, function(error) {
            toastr.error("Błąd podczas usuwania powiązania");
          })
        }
      });
    }

  }
})();
