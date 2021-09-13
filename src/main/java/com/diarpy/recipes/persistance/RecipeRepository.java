package com.diarpy.recipes.persistance;

import com.diarpy.recipes.businessLayer.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
