package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.commands.RecipeCommand;
import ahmad.recipe.sfrecipe.exceptions.NotFoundException;
import ahmad.recipe.sfrecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"/recipe/{id}/show"}, method = RequestMethod.GET)
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("title", "Show Recipe");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping(value = {"/recipe/new"}, method = RequestMethod.GET)
    public String newRecipe(Model model)
    {
        model.addAttribute("title", "Create Recipe");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping(value = {"recipe/{id}/update"}, method = RequestMethod.GET)
    public String updateRecipe(@PathVariable String id, Model model)
    {
        model.addAttribute("title", "Update Recipe");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @RequestMapping(value = {"/recipe"},method = RequestMethod.POST)
    public String savedOrUpdate(@ModelAttribute(value = "recipe") RecipeCommand recipeCommand)
    {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedRecipe.getId()+ "/show";
    }

    @RequestMapping(value = {"recipe/{id}/delete"},method = RequestMethod.GET)
    public String deleteById(@PathVariable String id)
    {
        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Model model){
        log.error("Handling not found exception");

        model.addAttribute("title", "404 Not Found Error");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");

        return modelAndView;

    }
}
