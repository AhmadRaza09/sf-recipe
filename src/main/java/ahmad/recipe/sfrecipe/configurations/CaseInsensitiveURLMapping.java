package ahmad.recipe.sfrecipe.configurations;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CaseInsensitiveURLMapping implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        antPathMatcher.setCaseSensitive(false);

        configurer.setPathMatcher(antPathMatcher);
    }
}
