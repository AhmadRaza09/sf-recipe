package ahmad.recipe.sfrecipe.controllers;


import ahmad.recipe.sfrecipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {

        String viewName = indexController.getIndexPage(model);

        assertEquals(viewName, "index");

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("title"), eq("Recipe Home"));
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}