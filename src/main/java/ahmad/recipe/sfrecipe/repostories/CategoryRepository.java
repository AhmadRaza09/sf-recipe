package ahmad.recipe.sfrecipe.repostories;

import ahmad.recipe.sfrecipe.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
