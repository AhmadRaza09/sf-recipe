package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
