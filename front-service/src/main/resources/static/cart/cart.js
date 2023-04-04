angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.CartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.createOrder = function () {
        $http.post(coreContextPath + 'api/v1/orders').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addToCart = function (product_id) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.CartId + '/add/' + product_id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.guestCreateOrder = function () {
        alert('Для оформления заказа необходимо войти в учетную запись');
    }

    $scope.clearCart = function (){
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.CartId + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (product_id) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.CartId + '/remove/' + product_id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeItemFromCart = function (product_id) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.CartId + '/delete/' + product_id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart();
});