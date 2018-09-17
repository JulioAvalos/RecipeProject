package com.recipe.project.springframework.services;

import com.recipe.project.springframework.commands.RecipeCommand;
import com.recipe.project.springframework.converters.RecipeCommandToRecipe;
import com.recipe.project.springframework.converters.RecipeToRecipeCommand;
import com.recipe.project.springframework.domain.Recipe;
import com.recipe.project.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand saveRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, saveRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), saveRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), saveRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), saveRecipeCommand.getIngredients().size());

    }

}
