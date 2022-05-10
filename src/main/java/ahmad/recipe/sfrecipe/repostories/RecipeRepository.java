package ahmad.recipe.sfrecipe.repostories;

import ahmad.recipe.sfrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository  extends CrudRepository<Recipe, Long> {
}
