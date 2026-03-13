package com.dealership.proiect.repository;

import com.dealership.proiect.model.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasinaRepository extends JpaRepository<Masina, Integer> {

    // --- 1. METODE GENERATE AUTOMAT DE SPRING (ORM Best Practice) ---
    // Nu mai scriem INSERT, UPDATE, DELETE manual. Folosim repository.save() si repository.deleteById()

    // --- 2. CITIRE CUSTOMIZATA ---
    @Query(value = "SELECT * FROM Masini ORDER BY Disponibilitate ASC, Marca ASC", nativeQuery = true)
    List<Masina> findAllNative();

    @Query(value = "SELECT * FROM Masini WHERE MasinaID = :id", nativeQuery = true)
    Masina findByIdNative(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Masini WHERE Marca LIKE %:keyword% OR Model LIKE %:keyword%", nativeQuery = true)
    List<Masina> searchNative(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Masini WHERE Disponibilitate = 'In Stoc'", nativeQuery = true)
    List<Masina> findDoarDisponibile();

    // --- 3. RAPOARTE STATISTICE COMPLEXE (Pastram SQL-ul pentru a impresiona la interviu) ---
    @Query(value = "SELECT TOP 1 * FROM Masini WHERE Pret < (SELECT AVG(Pret) FROM Masini) ORDER BY Pret DESC", nativeQuery = true)
    Masina findBestDeal();

    @Query(value = "SELECT * FROM Masini WHERE Kilometraj = (SELECT MIN(Kilometraj) FROM Masini)", nativeQuery = true)
    List<Masina> findMasinaNoua();

    // --- METODA RETURNATA PENTRU VANZARECONTROLLER ---
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @org.springframework.data.jpa.repository.Query(value = "UPDATE Masini SET Disponibilitate = 'Vandut' WHERE MasinaID = :id", nativeQuery = true)
    void marcheazaCaVanduta(@org.springframework.data.repository.query.Param("id") Integer id);

}