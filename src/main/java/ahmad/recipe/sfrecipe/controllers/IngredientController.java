package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.service.IngredientService;
import ahmad.recipe.sfrecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = {"recipe/{recipeId}/ingredients"}, method = RequestMethod.GET)
    public String listIngredient(@PathVariable String recipeId, Model model)
    {
        log.debug("Getting ingredient list for recipe recipeId: " + recipeId);

        model.addAttribute("title", "List Ingredients");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }

    @RequestMapping(value = {"/recipe/{recipeId}/ingredient/{ingredientId}/show"}, method = RequestMethod.GET)
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String ingredientId, Model model)
    {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId)
                        , Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

}
