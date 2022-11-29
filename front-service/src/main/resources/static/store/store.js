angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';


    // $scope.loadProducts = function () {
    //     $http.get(contextPath + 'api/v1/products').then(function (response) {
    //         $scope.products = response.data;
    //     });
    // };

    $scope.loadProducts = function (page = 1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: page,
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.products = response.data;
            $scope.generatePagesList(5);
        });
    };

    // $scope.showProductInfo = function (product_id){
    //     $http.get(contextPath + 'api/v1/products/' + product_id).then(function (response) {
    //         alert(response.data.title)
    //     });
    // }

    $scope.addToCart = function (id) {
        $http.get(cartContextPath + 'api/v1/cart/' + $localStorage.CartId + '/add/' + id)
            .then(function (response) {
            });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 1; i < totalPages + 1; i++) {
            out.push(i);
        }
        $scope.pagesList = out;
    }

    $scope.loadProducts();
});