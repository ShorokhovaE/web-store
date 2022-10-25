angular.module('app', ['ngStorage']).controller('IndexController', function ($scope, $http, $localStorage){

    if ($localStorage.springUser){
        try {
            let jwt = $localStorage.springUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime()/1000);
            if (currentTime>payload.exp){
                console.log("Token has expired");
                delete $localStorage.springUser;
                $http.defaults.headers.common.Authorization = '';

            }
        } catch (e){

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springUser.token;
    }

    $scope.tryToAuth = function (){
        $http.post('http://localhost:8189/store/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response){
            });
    }


    $scope.tryToLogout = function (){
        $scope.clearUser();
        $scope.user = null;
    }

    $scope.clearUser = function (){
        delete $localStorage.springUser;
        $http.defaults.headers.common.Authorization = '';
    }
    $scope.isLoggedIn = function (){
        if ($localStorage.springUser){
            return true;
        } else {
            return false;
        }
    }

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/store/api/v1/products').then(function (response) {
            $scope.products = response.data;
        });
    }

    $scope.deleteProduct = function (product_id) {
        $http.delete('http://localhost:8189/store/api/v1/products/' + product_id).then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/store/api/v1/products', $scope.newProduct).then(function (response) {
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    }

    $scope.showProductInfo = function (product_id){
        $http.get('http://localhost:8189/store/api/v1/products/' + product_id).then(function (response) {
            alert(response.data.title)
        });
    }

    $scope.removeFromCart = function (product_id) {
        $http.get('http://localhost:8189/store/api/v1/cart/remove/' + product_id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addToCart = function (product_id) {
        $http.get('http://localhost:8189/store/api/v1/cart/add/' + product_id).then(function (response) {
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

    $scope.removeItemFromCart = function (product_id) {
        $http.get('http://localhost:8189/store/api/v1/cart/delete/' + product_id).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createNewOrder = function (){
        $http.post('http://localhost:8189/store/api/v1/orders/create').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadProducts();
    $scope.loadCart();
});