(function () {
  'use strict';
  angular
    .module('frontend')
    .factory("AccountsFactory", AccountsFactory);

  function AccountsFactory($resource, accountsAPI) {
    return $resource(accountsAPI , {}, {
      register: {
        method: 'POST',
        isArray: false
      }
    });

  }
})();
