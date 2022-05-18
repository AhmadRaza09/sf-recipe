package ahmad.recipe.sfrecipe.controllers;

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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"recipe/{recipeId}/ingredients"}, method = RequestMethod.GET)
    public String listIngredient(@PathVariable String recipeId, Model model)
    {
        log.debug("Getting ingredient list for recipe recipeId: " + recipeId);

        model.addAttribute("title", "List Ingredients");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }

}
