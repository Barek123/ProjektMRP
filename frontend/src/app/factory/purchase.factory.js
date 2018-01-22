(function () {
    'use strict';
    angular
      .module('frontend')
      .factory("PurchaseFactory", PurchaseFactory);
  
    function PurchaseFactory($resource, purchaseAPI) {
      return $resource(purchaseAPI , {}, {
        getAll: {
            method: 'GET',
            isArray: true
        }
      });
  
    }
  })();
  