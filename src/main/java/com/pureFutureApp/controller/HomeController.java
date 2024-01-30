package com.pureFutureApp.controller;

import com.pureFutureApp.model.OfferHomeDTO;
import com.pureFutureApp.service.LoggedUser;
import com.pureFutureApp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public HomeController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        OfferHomeDTO offersFotHomePage = offerService.getOffersFotHomePage();

        modelAndView.addObject("offerHomeDTO", offersFotHomePage);

        return modelAndView;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
