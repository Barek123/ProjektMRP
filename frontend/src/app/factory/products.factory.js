(function () {
    'use strict';
    angular
      .module('frontend')
      .factory("ProductsFactory", ProductsFactory);
  
    function ProductsFactory($resource, productsAPI) {
      return $resource(productsAPI + ':id' , {
        id: '@id'
      }, {
        getAll: {
          method: 'GET',
          isArray: true
        },
        getMeasures: {
            method: 'GET',
            isArray: true,
            url: productsAPI + 'measures'
        },
        insert: {
            method: 'POST',
            isArray: false
        },
        edit: {
            method: 'PUT',
            isArray: false
        },
        expectOne: {
          method: 'GET',
          isArray: true,
          url: productsAPI+'except/:id'
        },
        delete: {
          method: 'DELETE',
          isArray: false
        }
      });
  
    }
  })();
  