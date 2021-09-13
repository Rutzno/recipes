package com.diarpy.recipes.presentation;

import com.diarpy.recipes.businessLayer.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@RestController
@RequestMapping("/api")
public class RecipeController {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();

    @PostMapping("/recipe/new")
    public Object postRecipe(@RequestBody Recipe recipe) {
        int id = recipeMap.size() + 1;
        recipeMap.put(id, recipe);
        return String.format("{\"id\": %d}", id);
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        if (id > recipeMap.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe not found for id = " + id);
        }
        return recipeMap.get(id);
    }
}
