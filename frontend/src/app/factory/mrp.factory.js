(function () {
    'use strict';
    angular
      .module('frontend')
      .factory("MrpFactory", MrpFactory);
  
    function MrpFactory($resource, mrpAPI) {
      return $resource(mrpAPI , {}, {
        insert: {
            method: 'POST',
            isArray: false
        }
      });
  
    }
  })();
  