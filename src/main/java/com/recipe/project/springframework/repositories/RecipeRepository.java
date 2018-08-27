package com.recipe.project.springframework.repositories;


import com.recipe.project.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
