package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"/recipe/show/{id}"}, method = RequestMethod.GET)
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("title", "Show Recipe");

        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

}
