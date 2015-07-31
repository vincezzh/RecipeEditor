<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>My Recipe</title>
    <link href="/myrecipe/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/myrecipe/jquery.cleditor.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/myrecipe/js/bootstrap.min.js"></script>
    <script src="/myrecipe/jquery.cleditor.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>
    <script src="/myrecipe/recipe-app.js"></script>
</head>
<body ng-app="recipeApp">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <ul class="nav navbar-nav">
            <li><a href="/myrecipe/index.jsp">Add</a></li>
            <li class="active"><a href="#">Review</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row" ng-controller="recipeListController">

    <div class="col-xs-3" style="margin-top: 60px;">
        <ul class="list-group">
            <li ng-repeat="recipe in recipes" ng-class="recipe.class">
                <span class="badge" ng-click="deleteRecipe($index, recipe.id)">X</span>
                <span ng-click="pickupRecipe($index)">{{recipe.title}}</span>
            </li>
        </ul>
    </div>

    <div class="hero-unit col-xs-9" style="margin-top: 60px;">
        <h1>Review Recipes <br/> <small><input type="text" placeholder="Title" ng-model="selectedRecipe.title"/></small></h1>
        <hr/>

        <textarea id="my_editor" name="my_editor"></textarea>
        <input type="hidden" ng-model="selectedRecipe.id"/>

        <div style="margin-top: 20px;">
            <input type="button" class="btn btn-primary" value="Update" ng-click="updateRecipe()"/>
        </div>
    </div>


    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        $("#my_editor").cleditor({height: 500});
    });
</script>
</html>