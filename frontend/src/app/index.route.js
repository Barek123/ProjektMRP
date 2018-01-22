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
	// }).when('/edit/:deck', {
	// 	templateUrl : 'app/edit_deck/edit_deck.html',
	// 	controller : 'EditDeckController',
	// 	controllerAs : 'vm'
	// }).when('/manage', {
	// 	templateUrl : 'app/manage/manage.html',
	// 	controller : 'ManageController',
	// 	controllerAs : 'vm'
	// }).when('/browse', {
	// 	templateUrl : 'app/browse/browse.html',
	// 	controller : 'BrowseController',
	// 	controllerAs : 'vm'
	}).otherwise('/home');
}
