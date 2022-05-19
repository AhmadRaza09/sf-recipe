package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.commands.IngredientCommand;
import ahmad.recipe.sfrecipe.converters.IngredientCommandToIngredient;
import ahmad.recipe.sfrecipe.converters.IngredientToIngredientCommand;
import ahmad.recipe.sfrecipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import ahmad.recipe.sfrecipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ahmad.recipe.sfrecipe.models.Ingredient;
import ahmad.recipe.sfrecipe.models.Recipe;
import ahmad.recipe.sfrecipe.repostories.RecipeRepository;
import ahmad.recipe.sfrecipe.repostories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImplTest()
    {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
                 new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(
                new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndingredientIdHappyPath()
    {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService
                .findByRecipeIdAndIngredientId(1L, 3l);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(anyLong());

    }

    @Test
    void testSavedRecipeCommand()
    {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Mockito.when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedIngredientCommand = ingredientService.savedIngredientCommand(ingredientCommand);

        //then
        assertEquals(Long.valueOf(3L), savedIngredientCommand.getId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(anyLong());
        Mockito.verify(recipeRepository, Mockito.times(1)).save(any());
    }
}
