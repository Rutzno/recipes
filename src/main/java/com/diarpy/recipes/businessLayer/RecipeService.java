package com.diarpy.recipes.businessLayer;

import com.diarpy.recipes.persistance.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe save(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                               .orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Recipe not found for id = " + id));
    }

    public void deleteRecipeById(Long id) {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }

    public void updateRecipe(Long id, Recipe recipe) {
        Recipe storedRecipe = findRecipeById(id);

        storedRecipe.setName(recipe.getName());
        storedRecipe.setCategory(recipe.getCategory());
        storedRecipe.setDescription(recipe.getDescription());
        storedRecipe.setIngredients(recipe.getIngredients());
        storedRecipe.setDirections(recipe.getDirections());

        recipeRepository.save(storedRecipe);
    }

    public List<Recipe> searchRecipesBy(String a, String param) {
        List<Recipe> recipes;
        switch (a) {
            case "category":
                recipes = recipeRepository.findRecipeByCategoryOrderByDateDesc(param);
                break;
            case "name":
                recipes = recipeRepository.findRecipeByNameOrderByDateDesc(param);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such name or category");
        }
        return recipes;
    }
}
