package com.dealership.proiect.controller;

import com.dealership.proiect.model.*;
import com.dealership.proiect.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VanzareController {

    @Autowired private VanzareRepository vanzareRepository;
    @Autowired private MasinaRepository masinaRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private FinantareRepository finantareRepository;

    @GetMapping("/vanzare-noua")
    public String arataFormularVanzare(Model model, HttpSession session) {
        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";
        model.addAttribute("masini", masinaRepository.findDoarDisponibile());
        model.addAttribute("clienti", clientRepository.findAllNative());
        model.addAttribute("vanzare", new Vanzare());
        model.addAttribute("clientNou", new Client());
        model.addAttribute("finantare", new Finantare());
        model.addAttribute("isClientNou", false);
        model.addAttribute("isFinantare", false);
        return "formular-vanzare";
    }

    @PostMapping("/proceseaza-vanzare")
    public String proceseazaVanzare(
            @Valid @ModelAttribute Vanzare vanzare,
            BindingResult result,
            @ModelAttribute("clientNou") Client clientNou,
            @ModelAttribute("finantare") Finantare finantare,
            @RequestParam(value = "isClientNou", required = false) boolean isClientNou,
            @RequestParam(value = "clientExistentId", required = false) Integer clientExistentId,
            @RequestParam(value = "isFinantare", required = false) boolean isFinantare,
            HttpSession session,
            Model model) {

        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";
        Angajat angajat = (Angajat) session.getAttribute("angajatLogat");

        Integer finalClientId = clientExistentId;

        if (isClientNou) {
            clientRepository.insertClientNative(
                    clientNou.getNume(), clientNou.getPrenume(), clientNou.getCnp(),
                    clientNou.getTelefon(), clientNou.getEmail(), clientNou.getAdresa()
            );
            Client c = clientRepository.findByCnpNative(clientNou.getCnp());
            finalClientId = c.getId();
        }

        vanzareRepository.insertVanzareNative(
                LocalDate.now(),
                vanzare.getPretFinal(),
                vanzare.getModalitatePlata(),
                vanzare.getMasina().getId(),
                finalClientId,
                angajat.getId()
        );

        masinaRepository.marcheazaCaVanduta(vanzare.getMasina().getId());

        return "redirect:/istoric-vanzari";
    }

    @GetMapping("/istoric-vanzari")
    public String istoric(
            Model model,
            HttpSession session,
            @RequestParam(value = "numeAngajat", required = false) String numeAngajat) {

        if (session.getAttribute("angajatLogat") == null) return "redirect:/login";

        // Tabel Principal
        List<Vanzare> lista = vanzareRepository.findAllNative();
        model.addAttribute("vanzari", lista);

        // Statistici Vechi
        model.addAttribute("bestDeal", masinaRepository.findBestDeal());
        List<Masina> lowKmList = masinaRepository.findMasinaNoua();
        model.addAttribute("lowKm", lowKmList.isEmpty() ? null : lowKmList.get(0));

        // Cautare Dinamica
        List<Vanzare> rezultateCautare = new ArrayList<>();
        if (numeAngajat != null && !numeAngajat.trim().isEmpty()) {
            rezultateCautare = vanzareRepository.findVanzariPerformanteAngajat(numeAngajat);
        }
        model.addAttribute("rezultateCautare", rezultateCautare);
        model.addAttribute("numeAngajat", numeAngajat);

        // --- CERINTE PROFA (DATE PENTRU NOILE TABELE) ---
        // Complexe
        model.addAttribute("marciPremium", vanzareRepository.findMarciPremium());
        model.addAttribute("clientiVip", vanzareRepository.findClientiVip());
        model.addAttribute("vanzariRecente", vanzareRepository.findVanzariMasiniNoiRecente());

        // Joins
        model.addAttribute("venituriCombustibil", vanzareRepository.findVenituriCombustibil());
        model.addAttribute("topAngajati", vanzareRepository.findTopAngajati());
        model.addAttribute("locatii", vanzareRepository.findVanzariPerLocatie());

        return "istoric-vanzari";
    }
}