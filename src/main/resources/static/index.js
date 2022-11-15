angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/store/api/v1/products').then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/store/api/v1/products/' + id).then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/store/api/v1/products', $scope.newProduct).then(function (response) {
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    }

    $scope.showProductInfo = function (id){
        $http.get('http://localhost:8189/store/api/v1/products/' + id).then(function (response) {
            alert(response.data.title)
        });
    }

    $scope.removeFromCart = function (id) {
        $http.get('http://localhost:8189/store/api/v1/cart/remove/' + id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addToCart = function (id) {
        $http.get('http://localhost:8189/store/api/v1/cart/add/' + id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/store/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function (){
        $http.get('http://localhost:8189/store/api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeItemFromCart = function (id) {
        $http.get('http://localhost:8189/store/api/v1/cart/delete/' + id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createNewOrder = function (){
        $http.get('http://localhost:8189/store/api/v1/cart/createOrder').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.loadProducts();
    $scope.loadCart();
});