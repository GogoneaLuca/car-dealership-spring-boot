/** Controller pt Login
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.controller;

import com.dealership.proiect.model.Angajat;
import com.dealership.proiect.repository.AngajatRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired private AngajatRepository angajatRepository;

    @GetMapping("/login")
    public String arataLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String proceseazaLogin(@RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  HttpSession session) {
        Angajat angajat = angajatRepository.findByEmailAndParola(email, password);

        if (angajat != null) {
            session.setAttribute("angajatLogat", angajat);
            return "redirect:/";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}