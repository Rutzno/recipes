package com.diarpy.recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.diarpy.recipes.persistance.RecipeRepository;
import com.diarpy.recipes.persistance.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public Recipe save(Recipe newRecipe, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName());
        newRecipe.setUser(user);
        return recipeRepository.save(newRecipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Recipe not found for id = " + id));
    }

    public void deleteRecipeById(Long id, Authentication authentication) {
        Recipe recipe = findRecipeById(id);
        User user = userRepository.findByEmail(authentication.getName());
        if (recipe.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "You are not allowed");
        }
        recipeRepository.delete(recipe);
    }

    public void updateRecipe(Long id, Recipe recipe, Authentication authentication) {
        Recipe storedRecipe = findRecipeById(id);
        User user = userRepository.findByEmail(authentication.getName());
        if (storedRecipe.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "You are not allowed");
        }
        storedRecipe.setName(recipe.getName());
        storedRecipe.setCategory(recipe.getCategory());
        storedRecipe.setDescription(recipe.getDescription());
        storedRecipe.setIngredients(recipe.getIngredients());
        storedRecipe.setDirections(recipe.getDirections());
        storedRecipe.setDate(LocalDateTime.now());
        recipeRepository.save(storedRecipe);
    }

    public List<Recipe> searchRecipesBy(Map<String, String> allParams) {
        List<Recipe> recipes;
        if (allParams.size() > 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such params");
        String a;
        if (allParams.containsKey("name")) {
            a = "name";
            String param = allParams.get(a);
            recipes = recipeRepository.findByName(param);
//            recipes = recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(param);
        } else if (allParams.containsKey("category")) {
            a = "category";
            String param = allParams.get(a);
            recipes = recipeRepository.findByCategory(param);
//            recipes = recipeRepository.findByCategoryLikeIgnoreCaseOrderByDateDesc(param);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such params");
        }
        return recipes;
    }
}
