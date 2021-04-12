
package pl.kursant.oskoffice.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageController {
    
    @GetMapping(value = "")
    public ModelAndView getIndexView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("index");
        return model;
    }
    
    @GetMapping(value = "onas")
    public ModelAndView getAboutUsView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("aboutus");
        return model;
    }
    
    @GetMapping(value = "cennik")
    public ModelAndView getPriceView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("price");
        return model;
    }
    
    @GetMapping(value = "galeria")
    public ModelAndView getGalleryView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("gallery");
        return model;
    }
    
    @GetMapping(value = "kontakt")
    public ModelAndView getContactView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("contact");
        return model;
    }
    
    @GetMapping(value = "artykul-poradnik")
    public ModelAndView getArticleGuideView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("article-guide");
        return model;
    }
    
    @GetMapping(value = "artykul-zdawalnosc")
    public ModelAndView getArticlePassView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("article-pass");
        return model;
    }
    
    @GetMapping(value = "login")
    public ModelAndView getLoginView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("login");
        return model;
    }
    
}
