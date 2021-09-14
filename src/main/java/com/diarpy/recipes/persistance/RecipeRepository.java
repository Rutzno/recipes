package com.diarpy.recipes.persistance;

import com.diarpy.recipes.businessLayer.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findRecipeByCategoryOrderByDateDesc(String category);
    List<Recipe> findRecipeByNameOrderByDateDesc(String name);
}
