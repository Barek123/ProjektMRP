angular.module('frontend').config(routerConfig);

/** @ngInject */
function routerConfig($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : 'app/main/main.html',
		controller : 'MainController',
		controllerAs : 'vm'
	}).when('/products', {
		templateUrl : 'app/products/products.html',
		controller : 'ProductsController',
		controllerAs : 'vm'
	}).when('/connections', {
		templateUrl : 'app/connections/connections.html',
		controller : 'ConnectionsController',
		controllerAs : 'vm'
	}).when('/purchases', {
		templateUrl : 'app/purchases/purchases.html',
		controller : 'PurchasesController',
		controllerAs : 'vm'
	}).otherwise('/home');
}
