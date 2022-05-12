package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value ={"", "/", "/index", "/index.html"} , method = RequestMethod.GET)
     public String getIndexPage(Model model) {

        log.debug("Getting Index page.");
//        set title
        model.addAttribute("title", "Recipe Home");

        model.addAttribute("recipes", recipeService.getRecipes());

         return "index";
     }
}
