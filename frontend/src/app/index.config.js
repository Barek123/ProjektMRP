(function () {
  'use strict';

  angular
    .module('frontend')
    .config(config)
    .factory('sessionInjector', function ($cookies) {
      return {
        request: function (config) {
          if ($cookies.get('token')) {
            config.headers['Authorization'] = $cookies.get('token');
            config.headers['Content-Type'] = 'application/json;charset=utf-8';
            config.headers['Accept'] = 'application/json, */*';
          }
          return config;
        }
      };
    });

  /** @ngInject */
  function config($locationProvider, $logProvider, toastrConfig, $httpProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-bottom-right';
    toastrConfig.preventDuplicates = false;
    toastrConfig.progressBar = true;
    $httpProvider.interceptors.push('sessionInjector');
    // $qProvider.errorOnUnhandledRejections(false);
  }

})();
