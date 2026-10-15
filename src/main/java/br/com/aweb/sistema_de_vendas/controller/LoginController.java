package br.com.aweb.sistema_de_vendas.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    // Página de login (o POST /login é tratado pelo Spring Security)
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "login";
    }

    // Página de confirmação de saída (o POST /logout é tratado pelo Spring Security)
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Página inicial: ADMIN vai para clientes, PADRAO para pedidos
    @GetMapping("/")
    public String inicio(Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return admin ? "redirect:/clientes" : "redirect:/pedidos";
    }

    @GetMapping("/acesso-negado")
    public ModelAndView acessoNegado() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 403);
        mav.addObject("titulo", "Acesso negado");
        mav.addObject("mensagem", "Seu perfil não tem permissão para acessar esta página.");
        return mav;
    }
}
