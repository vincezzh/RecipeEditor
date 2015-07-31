package com.akhaltech.controller;

import com.akhaltech.model.Recipe;
import com.akhaltech.rest.RestResponse;
import com.akhaltech.service.RecipeService;
import org.parse4j.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vince on 2015-07-14.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @ResponseBody
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST, produces = "application/json")
    public RestResponse<String> addOrUpdateRecipe(@RequestBody Recipe recipe) {
        try {
            if(recipe.getId() == null)
                recipeService.addRecipe(recipe);
            else
                recipeService.updateRecipe(recipe);
        } catch (ParseException e) {
            return new RestResponse<String>(true, "failed", null);
        }
        return new RestResponse<String>(true, "success", null);
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestResponse<Recipe> getRecipe(@PathVariable("id") String id) {
        Recipe recipe = null;
        try {
            recipe = recipeService.getRecipe(id);
        } catch (ParseException e) {
            return new RestResponse<Recipe>(true, "failed", null);
        }
        return new RestResponse<Recipe>(true, "success", recipe);
    }

    @ResponseBody
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = "application/json")
    public RestResponse<List<Recipe>> getAllRecipe() {
        List<Recipe> recipeList = null;
        try {
            recipeList = recipeService.getAllRecipe();
        } catch (ParseException e) {
            return new RestResponse<List<Recipe>>(true, "failed", null);
        }
        return new RestResponse<List<Recipe>>(true, "success", recipeList);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestResponse<String> deleteRecipe(@PathVariable("id") String id) {
        try {
            recipeService.deleteRecipe(id);
        } catch (ParseException e) {
            return new RestResponse<String>(true, "failed", null);
        }
        return new RestResponse<String>(true, "success", null);
    }
}
