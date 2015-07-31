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
            <li class="active"><a href="#">Add</a></li>
            <li><a href="/myrecipe/list.jsp">Review</a></li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top: 60px;" ng-controller="recipeAddController">
    <div class="hero-unit">
        <div class="hero-unit col-xs-12">
            <h1>Add a Recipe <br/> <small><input type="text" placeholder="Title" ng-model="newRecipe.title"/></small></h1>
            <hr/>

            <textarea id="my_editor" name="my_editor"></textarea>

            <div style="margin-top: 20px;">
                <input type="button" class="btn btn-primary" value="Save" ng-click="addRecipe()"/>
            </div>
        </div>
    </div>

</div>
<div id="fb-root"></div>
</body>
<script>
    $(document).ready(function () {
        $("#my_editor").cleditor({height: 500});
    });
</script>
</html>