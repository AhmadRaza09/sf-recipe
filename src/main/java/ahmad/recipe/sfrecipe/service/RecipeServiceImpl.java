package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.models.Recipe;
import ahmad.recipe.sfrecipe.repostories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipes = new HashSet<>();

//      add all recipes to set
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }
}
