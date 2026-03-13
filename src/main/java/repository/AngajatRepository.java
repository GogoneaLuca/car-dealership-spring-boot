package com.dealership.proiect.repository;

import com.dealership.proiect.model.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Integer> {
    Angajat findByEmailAndParola(String email, String parola);
}