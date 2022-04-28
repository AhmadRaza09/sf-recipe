package ahmad.recipe.sfrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value ={"", "/", "/index", "/index.html"} , method = RequestMethod.GET)
     public String getIndexPage(Model model) {

        model.addAttribute("title", "Recipe Home");
         return "index";
     }
}
