package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.commands.RecipeCommand;
import ahmad.recipe.sfrecipe.converters.RecipeCommandToRecipe;
import ahmad.recipe.sfrecipe.converters.RecipeToRecipeCommand;
import ahmad.recipe.sfrecipe.models.Recipe;
import ahmad.recipe.sfrecipe.repostories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {

        log.debug("I'm in the service");

        Set<Recipe> recipes = new HashSet<>();

//      add all recipes to set
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        log.debug("saved RecipeId: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
