var app = angular.module('productApp', []);

app.controller('productController', function($scope,$http){
	
	$scope.products = [];
	
	$scope.mycarts = [];
	
	$scope.addToCart = function(product){
		$scope.displayCart = false;
		if($scope.mycarts.filter(function(val){
		    return val === product;
		}).length < product.count ){
			$scope.mycarts.push(product);
		}else{
			alert('This product is not avilable');
		}
	};
	
	
	_refreshPageData();
	
	function _refreshPageData(){
		$http({
			method : 'GET',
			url : '../services/productService/getproducts'
		}).then(function successCallback(response){
			$scope.products = response.data;
		}, function failureCallback(response){
			
		});
	}
	
	$scope.showCart = function(){
		$scope.displayCart = true;
	};
	
	$scope.buy = function(){
		$http({
			method : 'POST',
			url : '../services/productService/buyproducts',
			data : angular.toJson($scope.mycarts),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response){
			alert('Thank you!! I love you Payel... :)');
			$scope.mycarts = [];
			_refreshPageData();
		}, function errorCallback(response){
			
		});
	};
	
});