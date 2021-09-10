package com.diarpy.recipes;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class RecipeController {
    private Recipe recipe = new Recipe(
            "Fresh Mint Tea",
            "Light, aromatic and refreshing beverage, ...",
            "boiled water, honey, fresh mint leaves",
            "1) Boil water. 2) Pour boiling hot water into a mug. 3) Add fresh mint leaves. 4) Mix and let the mint leaves seep for 3-5 minutes. 5) Add honey and mix again."
    );

    @PostMapping("/recipe")
    public void postRecipe(@RequestBody Recipe r) {
        recipe = r;
    }

    @GetMapping("/recipe")
    public Recipe getRecipe() {
        return recipe;
    }
}
