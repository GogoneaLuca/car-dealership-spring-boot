/** Controller pt Angajat
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.controller;

import com.dealership.proiect.model.Angajat;
import com.dealership.proiect.repository.AngajatRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class AngajatController {

    @Autowired private AngajatRepository angajatRepository;

    private boolean esteAdmin(HttpSession session) {
        Angajat a = (Angajat) session.getAttribute("angajatLogat");
        return a != null && "Manager".equalsIgnoreCase(a.getFunctie());
    }

    @GetMapping("/angajati")
    public String listaAngajati(Model model, HttpSession session) {
        if (!esteAdmin(session)) return "redirect:/";

        model.addAttribute("userLogat", session.getAttribute("angajatLogat"));
        model.addAttribute("angajati", angajatRepository.findAll());
        return "angajati";
    }

    @GetMapping("/angajati/adaugare")
    public String formularAdaugare(Model model, HttpSession session) {
        if (!esteAdmin(session)) return "redirect:/";

        model.addAttribute("angajat", new Angajat());
        model.addAttribute("userLogat", session.getAttribute("angajatLogat"));
        return "adaugare-angajat";
    }

    @PostMapping("/angajati/salvare")
    public String salvareAngajat(@Valid @ModelAttribute Angajat angajat,
                                 BindingResult result,
                                 HttpSession session,
                                 Model model) {
        if (!esteAdmin(session)) return "redirect:/";

        if (result.hasErrors()) {
            model.addAttribute("userLogat", session.getAttribute("angajatLogat"));
            return "adaugare-angajat";
        }

        if (angajat.getDataAngajare() == null) {
            angajat.setDataAngajare(LocalDate.now());
        }

        angajatRepository.save(angajat);
        return "redirect:/angajati";
    }

    @GetMapping("/angajati/editare/{id}")
    public String editareAngajat(@PathVariable Integer id, Model model, HttpSession session) {
        if (!esteAdmin(session)) return "redirect:/";

        var angajat = angajatRepository.findById(id).orElse(null);
        if (angajat != null) {
            model.addAttribute("angajat", angajat);
            model.addAttribute("userLogat", session.getAttribute("angajatLogat"));
            return "adaugare-angajat";
        }
        return "redirect:/angajati";
    }

    @GetMapping("/angajati/stergere/{id}")
    public String stergereAngajat(@PathVariable Integer id, HttpSession session) {
        if (!esteAdmin(session)) return "redirect:/";
        try {
            angajatRepository.deleteById(id);
        } catch (Exception e) {
            // Tratare eroare
        }
        return "redirect:/angajati";
    }
}