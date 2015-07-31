package com.akhaltech.service;

import com.akhaltech.model.Recipe;
import org.parse4j.ParseException;

import java.util.List;

/**
 * Created by vince on 2015-07-14.
 */
public interface RecipeService {
    void addRecipe(Recipe recipe) throws ParseException;
    void updateRecipe(Recipe recipe) throws ParseException;
    Recipe getRecipe(String id) throws ParseException;
    List<Recipe> getAllRecipe() throws ParseException;
    void deleteRecipe(String id) throws ParseException;
}
