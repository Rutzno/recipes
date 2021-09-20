package com.diarpy.recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.diarpy.recipes.businessLayer.Recipe;
import com.diarpy.recipes.businessLayer.RecipeService;
import com.diarpy.recipes.businessLayer.User;
import com.diarpy.recipes.businessLayer.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecipeController {
    final RecipeService recipeService;
    final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService,
                            UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping("/recipe/new")
    public Object postRecipe(@Valid @RequestBody Recipe recipe, Authentication authentication) {
        recipe.setDate(LocalDateTime.now());
        Recipe newRecipe = recipeService.save(recipe, authentication);
        return String.format("{\"id\": %d}", newRecipe.getId());
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    @DeleteMapping("/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id, Authentication authentication) {
        recipeService.deleteRecipeById(id, authentication);
    }

    @PutMapping("/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe, Authentication authentication) {
        recipeService.updateRecipe(id, recipe, authentication);
    }

    @GetMapping("/recipe/search/")
    public List<Recipe> searchRecipeByCategory(@RequestParam Map<String, String> allParams) {
        return recipeService.searchRecipesBy(allParams);
    }

    @PostMapping(path = "/register")
    public void register(@Valid @RequestBody User user) {
        userService.register(user);
    }
}
