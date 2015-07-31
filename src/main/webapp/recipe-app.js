var recipeApp = angular.module('recipeApp', []);

recipeApp.controller('recipeListController', ['$scope', 'recipeService', function ($scope, recipeService) {

    loadRecipe();

    function loadRecipe() {
        recipeService.getAllRecipe()
            .success(function (response) {
                init(response.data);
            });
    }

    function init(data) {
        $scope.recipes = data;

        for(var i=0; i<$scope.recipes.length; i++) {
            if(i == 0)
                $scope.recipes[i].class = "list-group-item active";
            else
                $scope.recipes[i].class = "list-group-item";
        }

        $scope.selectedRecipe = $scope.recipes[0];
        $('#my_editor').val($scope.selectedRecipe.content).blur();
    }

    $scope.pickupRecipe = function(index) {
        $scope.selectedRecipe.class = 'list-group-item';
        $scope.selectedRecipe = $scope.recipes[index];
        $scope.selectedRecipe.class = 'list-group-item active';
        $('#my_editor').val($scope.selectedRecipe.content).blur();
    }

    $scope.updateRecipe = function() {
        $scope.selectedRecipe.content = $('#my_editor').val();

        var updatedRecipe = {};
        updatedRecipe.id = $scope.selectedRecipe.id;
        updatedRecipe.title = $scope.selectedRecipe.title;
        updatedRecipe.content = $scope.selectedRecipe.content;
        recipeService.addOrUpdateRecipe(updatedRecipe)
            .success(function (response) {
                alert(response.message);
            });
    }

    $scope.deleteRecipe = function(index) {
        var recipe = $scope.recipes[index];
        if(confirm("Are you want to delete recipe: " + recipe.title + "?")) {
            recipeService.deleteRecipe(recipe.id)
                .success(function(response) {
                    if(response.message == "success") {
                        $scope.recipes.splice(index, 1);
                        alert(response.message);
                    }
                });
        }
    }
}]);

recipeApp.controller('recipeAddController', ['$scope', 'recipeService', function ($scope, recipeService) {

    $scope.newRecipe = {};

    $scope.addRecipe = function() {
        $scope.newRecipe.content = $('#my_editor').val();

        var newRecipe = {};
        newRecipe.title = $scope.newRecipe.title;
        newRecipe.content = $scope.newRecipe.content;
        recipeService.addOrUpdateRecipe(newRecipe)
            .success(function (response) {
                alert(response.message);
            });
    }
}]);

recipeApp.service('recipeService', ['$http', function($http) {
    var urlBase = '/myrecipe/recipe';

    var dataFactory = {};
    dataFactory.addOrUpdateRecipe = function(data) {
        var configuration = {
            method: 'POST',
            url: urlBase + '/addOrUpdate',
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain, */*; q=0.01"
            },
            data: data
        };
        return $http(configuration);
    };

    dataFactory.getRecipe = function(objectId) {
        var configuration = {
            method: 'GET',
            url: urlBase + '/get/' + objectId,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain, */*; q=0.01"
            }
        };
        return $http(configuration);
    };

    dataFactory.getAllRecipe = function() {
        var configuration = {
            method: 'GET',
            url: urlBase + '/getall',
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain, */*; q=0.01"
            }
        };
        return $http(configuration);
    };

    dataFactory.deleteRecipe = function(objectId) {
        var configuration = {
            method: 'GET',
            url: urlBase + '/delete/' + objectId,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain, */*; q=0.01"
            }
        };
        return $http(configuration);
    };

    return dataFactory;
}]);