package com.diarpy.recipes;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class RecipeController {
    private Recipe recipe;

    @PostMapping("/recipe")
    public void postRecipe(@RequestBody Recipe r) {
        recipe = r;
    }

    @GetMapping("/recipe")
    public Recipe getRecipe() {
        return recipe;
    }
}
