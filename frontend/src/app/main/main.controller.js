(function () {
  'use strict';

  angular
    .module('frontend')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController(LoginFactory, $cookies, $location) {
    var vm = this;
    vm.user = {};

    vm.login = login;
    vm.go = go;


    function login() {
      LoginFactory.login({}, vm.user, function (data) {
        $cookies.put('token', data.token);
        go('/products');
      });
    }

    function go(path) {
      $location.path(path);
    }
  }
})();
