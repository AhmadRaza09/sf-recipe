package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.models.Category;
import ahmad.recipe.sfrecipe.models.UnitOfMeasure;
import ahmad.recipe.sfrecipe.repostories.CategoryRepository;
import ahmad.recipe.sfrecipe.repostories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping(value ={"", "/", "/index", "/index.html"} , method = RequestMethod.GET)
     public String getIndexPage(Model model) {

//        set title
        model.addAttribute("title", "Recipe Home");

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category id is: " + categoryOptional.get().getDescription());
        System.out.println("UOM id is: " + unitOfMeasureOptional.get().getDescription());
         return "index";
     }
}
