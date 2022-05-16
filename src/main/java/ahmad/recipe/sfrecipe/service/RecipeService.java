package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
