package com.diarpy.recipes.presentation;

import com.diarpy.recipes.businessLayer.Recipe;
import com.diarpy.recipes.businessLayer.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

/**
 * Exampe of recipe
 {
 "name": "Fresh Mint Tea",
 "category": "beverage",
 "description": "Light, aromatic and refreshing beverage, ...",
 "ingredients": ["boiled water", "honey", "fresh mint leaves"],
 "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
 }
 */

@RestController
@RequestMapping("/api")
public class RecipeController {
    final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe/new")
    public Object postRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.save(recipe);
        return String.format("{\"id\": %d}", newRecipe.getId());
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    @DeleteMapping("/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
    }

    @PutMapping("/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }

    @GetMapping("/recipe/search/")
    public List<Recipe> searchRecipeByCategory(@RequestParam Map<String, String> allParams) {
        return recipeService.searchRecipesBy(allParams);
    }

}
