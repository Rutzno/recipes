package com.diarpy.recipes.persistance;

import com.diarpy.recipes.businessLayer.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
