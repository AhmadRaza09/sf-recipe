package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.commands.IngredientCommand;
import ahmad.recipe.sfrecipe.commands.RecipeCommand;
import ahmad.recipe.sfrecipe.commands.UnitOfMeasureCommand;
import ahmad.recipe.sfrecipe.service.IngredientService;
import ahmad.recipe.sfrecipe.service.RecipeService;
import ahmad.recipe.sfrecipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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


    @RequestMapping(value = {"recipe/{recipeId}/ingredient/new"}, method = RequestMethod.GET)
    public String newIngredient(@PathVariable String recipeId, Model model){

        log.debug("Add new Ingredient");
        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @RequestMapping(value = {"/recipe/{recipeId}/ingredient/{ingredientId}/update"}
            , method = RequestMethod.GET)
    public String updateRecipeIngredient(@PathVariable String recipeId
            , @PathVariable String ingredientId, Model model)
    {
        model.addAttribute("title", "Update Ingredient");

        model.addAttribute("ingredient", ingredientService.
                findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }


    @RequestMapping(value = {"recipe/{recipeId}/ingredient"}, method = RequestMethod.POST)
    public String saveorUpdate(@ModelAttribute IngredientCommand ingredientCommand)
    {
        IngredientCommand savedCommand = ingredientService.savedIngredientCommand(ingredientCommand);

        log.debug("saved recipe id: " + savedCommand.getRecipeId());
        log.debug("saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

}
