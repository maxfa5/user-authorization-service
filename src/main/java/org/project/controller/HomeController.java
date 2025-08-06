package org.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/secured")
    public String secured(Model model, 
                         @AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("attributes", principal.getAttributes());
        return "secured";
    }
}
