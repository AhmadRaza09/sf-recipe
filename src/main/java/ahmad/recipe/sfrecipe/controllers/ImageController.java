package ahmad.recipe.sfrecipe.controllers;

import ahmad.recipe.sfrecipe.service.ImageService;
import ahmad.recipe.sfrecipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"recipe/{id}/image"}, method = RequestMethod.GET)
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("title", "Image Upload Form");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @RequestMapping(value = {"recipe/{id}/image"}, method = RequestMethod.POST)
    public String handleImagePost(@PathVariable String id, @RequestParam("imageFile") MultipartFile multipartFile){
        imageService.saveImageFile(Long.valueOf(id), multipartFile);

        return "redirect:/recipe/" + id + "/show";
    }


}
