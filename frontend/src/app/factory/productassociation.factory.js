(function () {
    'use strict';
    angular
      .module('frontend')
      .factory("ProductsAssociationFactory", ProductsAssociationFactory);
  
    function ProductsAssociationFactory($resource, productsAssociationAPI) {
      return $resource(productsAssociationAPI + ':id/:sPart/:tPart' , {
        id: '@id',
        secendPart: "@sPart",
        thirdPart: "@tPart",
        childId: "@childId"
      }, {
        getObject: {
          method: 'GET',
          isArray: false
        },
        insert: {
          method: 'POST',
          isArray: false
        },
        delete: {
          url: productsAssociationAPI + 'parent/:id/child/:childId',
          method: 'DELETE',
          isArray: false
        }
      });
  
    }
  })();
  