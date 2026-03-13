package com.dealership.proiect.controller;

import com.dealership.proiect.model.Masina;
import com.dealership.proiect.repository.MasinaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MasinaController {

    @Autowired
    private MasinaRepository masinaRepository;

    @GetMapping("/")
    public String dashboard(Model model, @RequestParam(value = "keyword", required = false) String keyword, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";

        List<Masina> listaMasini = (keyword != null && !keyword.trim().isEmpty())
                ? masinaRepository.searchNative(keyword)
                : masinaRepository.findAllNative();

        model.addAttribute("masini", listaMasini);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/adauga-masina")
    public String arataFormularAdaugare(Model model, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";
        model.addAttribute("masina", new Masina());
        return "adaugare-masina";
    }

    @PostMapping("/salveaza-masina")
    public String salveazaMasina(@Valid @ModelAttribute("masina") Masina masina, BindingResult result, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";
        if (result.hasErrors()) return "adaugare-masina";

        try {
            // Best Practice: Spring Data JPA "save" face automat INSERT daca id e null, sau UPDATE daca exista deja.
            if(masina.getDisponibilitate() == null) {
                masina.setDisponibilitate("In Stoc");
            }
            masinaRepository.save(masina);
        } catch (Exception e) {
            System.err.println("Eroare la salvare masina: " + e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";

        masinaRepository.findById(id).ifPresent(m -> model.addAttribute("masina", m));
        return "adaugare-masina";
    }

    @GetMapping("/sterge/{id}")
    public String stergeMasina(@PathVariable("id") Integer id, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";

        try {
            masinaRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Nu se poate sterge masina (Foreign Key constraint): " + e.getMessage());
        }

        return "redirect:/";
    }
}