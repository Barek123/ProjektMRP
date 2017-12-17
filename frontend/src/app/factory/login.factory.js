(function () {
  'use strict';
  angular
    .module('frontend')
    .factory("LoginFactory", LoginFactory);

  function LoginFactory($resource, loginAPI) {
    return $resource(loginAPI , {}, {
    	login: {
            method: 'POST',
            isArray: false
          }
    });

  }
})();
