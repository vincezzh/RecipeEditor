package com.akhaltech.service.impl;

import com.akhaltech.model.Recipe;
import com.akhaltech.service.RecipeService;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 2015-07-14.
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private static final String APP_ID = "azmEmnzeNaoN9WcRpcJd9ov3gUZfsuhnwQ6h4Vlo";
    private static final String APP_REST_API_ID = "juGTvKFV2fWavyHG5YKov19hA1lm8HXNO6YJZrcI";
    private static final String RECIPE_PARSE_NAME = "Recipe";
    private static final String STATUS_ACTIVE = "Active";
    private static final String STATUS_INACTIVE = "Inactive";

    @Override
    public void addRecipe(Recipe recipe) throws ParseException {
        Parse.initialize(APP_ID, APP_REST_API_ID);
        ParseObject recipeParseObj = new ParseObject(RECIPE_PARSE_NAME);
        recipeParseObj.put("title", recipe.getTitle());
        recipeParseObj.put("content", recipe.getContent());
        recipeParseObj.put("status", STATUS_ACTIVE);
        recipeParseObj.save();
    }

    @Override
    public void updateRecipe(Recipe recipe) throws ParseException {
        Parse.initialize(APP_ID, APP_REST_API_ID);
        ParseObject recipeParseObj = new ParseObject(RECIPE_PARSE_NAME);
        recipeParseObj.setObjectId(recipe.getId());
        recipeParseObj.put("title", recipe.getTitle());
        recipeParseObj.put("content", recipe.getContent());
        recipeParseObj.save();
    }

    @Override
    public Recipe getRecipe(String id) throws ParseException {
        Parse.initialize(APP_ID, APP_REST_API_ID);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(RECIPE_PARSE_NAME);
        ParseObject result = query.get(id);

        Recipe recipe = new Recipe();
        recipe.setId(result.getObjectId());
        recipe.setTitle(result.getString("title"));
        recipe.setContent(result.getString("content"));
        recipe.setCreatedDate(result.getCreatedAt());

        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipe() throws ParseException {
        Parse.initialize(APP_ID, APP_REST_API_ID);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(RECIPE_PARSE_NAME);
        query.whereEqualTo("status", STATUS_ACTIVE);
        query.orderByDescending("updatedAt");
        List<ParseObject> resultList = query.find();

        List<Recipe> recipeList = null;
        if(resultList != null && resultList.size() > 0) {
            recipeList = new ArrayList<Recipe>();
            for(ParseObject result : resultList) {
                Recipe recipe = new Recipe();
                recipe.setId(result.getObjectId());
                recipe.setTitle(result.getString("title"));
                recipe.setContent(result.getString("content"));
                recipe.setCreatedDate(result.getCreatedAt());

                recipeList.add(recipe);
            }
        }

        return recipeList;
    }

    @Override
    public void deleteRecipe(String id) throws ParseException {
        Parse.initialize(APP_ID, APP_REST_API_ID);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(RECIPE_PARSE_NAME);
        ParseObject result = query.get(id);
        result.put("status", STATUS_INACTIVE);
        result.save();
    }
}
