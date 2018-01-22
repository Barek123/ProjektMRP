/* global malarkey:false, moment:false */
(function () {
  'use strict';

  angular
    .module('frontend')
    .constant('malarkey', malarkey)
    .constant('accountsAPI', '/api/accounts/')
    .constant('loginAPI', '/api/login/')
    .constant('productsAPI', '/api/products/')
    .constant('mrpAPI', '/api/mrp/')
    .constant('purchaseAPI', '/api/purchasehistory')
    .constant('productsAssociationAPI', '/api/productsassociation/')
    .constant('moment', moment);
})();
