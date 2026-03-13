package com.dealership.proiect.repository;

import com.dealership.proiect.model.Vanzare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDate;

public interface VanzareRepository extends JpaRepository<Vanzare, Integer> {

    // --- INTEROGARI DE BAZA (CRUD) ---
    @Query(value = "SELECT * FROM Vanzari ORDER BY DataVanzare DESC", nativeQuery = true)
    List<Vanzare> findAllNative();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Vanzari (DataVanzare, PretFinal, ModalitatePlata, MasinaID, ClientID, AngajatID) " +
            "VALUES (:data, :pret, :plata, :masinaId, :clientId, :angajatId)", nativeQuery = true)
    void insertVanzareNative(@Param("data") LocalDate data, @Param("pret") Double pret,
                             @Param("plata") String plata, @Param("masinaId") Integer masinaId,
                             @Param("clientId") Integer clientId, @Param("angajatId") Integer angajatId);

    // =================================================================================
    // =================================================================================

    // --- COMPLEXA 1: Performanta Angajat (JOIN + SUBCERERE AVG) ---
    @Query(value = """
        SELECT v.* FROM Vanzari v
        JOIN Angajati a ON v.AngajatID = a.AngajatID
        WHERE a.Nume LIKE %:nume%
        AND v.PretFinal >= (SELECT AVG(PretFinal) FROM Vanzari)
    """, nativeQuery = true)
    List<Vanzare> findVanzariPerformanteAngajat(@Param("nume") String nume);

    // --- COMPLEXA 2: Marci Premium (JOIN + GROUP BY + HAVING + SUBCERERE) ---
    @Query(value = """
        SELECT m.Marca, COUNT(v.VanzareID) as NrVanzari
        FROM Vanzari v
        JOIN Masini m ON v.MasinaID = m.MasinaID
        GROUP BY m.Marca
        HAVING AVG(v.PretFinal) > (SELECT AVG(PretFinal) FROM Vanzari)
    """, nativeQuery = true)
    List<Object[]> findMarciPremium();

    // --- COMPLEXA 3: Clienti VIP (SUBCERERE IMBRICATA / NESTED SUBQUERY) ---
    // Selecteaza clientii care au cumparat masini mai scumpe decat media masinilor BMW vandute
    @Query(value = """
        SELECT c.Nume, c.Prenume, v.PretFinal
        FROM Clienti c
        JOIN Vanzari v ON c.ClientID = v.ClientID
        WHERE v.PretFinal > (
            SELECT AVG(v2.PretFinal) 
            FROM Vanzari v2 
            JOIN Masini m2 ON v2.MasinaID = m2.MasinaID 
            WHERE m2.Marca = 'BMW'
        )
    """, nativeQuery = true)
    List<Object[]> findClientiVip();

    // --- COMPLEXA 4: Vanzari "Recente" cu Masini "Noi" (MULTIPLE CONDITIONS & SUBQUERY) ---
    // Vanzari din ultimele 30 zile pentru masini produse dupa 2020
    @Query(value = """
        SELECT m.Marca, m.Model, v.DataVanzare
        FROM Vanzari v
        JOIN Masini m ON v.MasinaID = m.MasinaID
        WHERE m.AnFabricatie > 2020
        AND v.DataVanzare > (SELECT DATEADD(day, -365, GETDATE())) 
    """, nativeQuery = true)
    List<Object[]> findVanzariMasiniNoiRecente();

    // --- JOIN 1: Venituri pe Tip Combustibil (JOIN Vanzari-Masini) ---
    @Query(value = """
        SELECT m.Combustibil, SUM(v.PretFinal) 
        FROM Vanzari v 
        JOIN Masini m ON v.MasinaID = m.MasinaID 
        GROUP BY m.Combustibil
    """, nativeQuery = true)
    List<Object[]> findVenituriCombustibil();

    // --- JOIN 2: Top Angajati dupa Volum (JOIN Vanzari-Angajati) ---
    @Query(value = """
        SELECT a.Nume, COUNT(*) 
        FROM Vanzari v 
        JOIN Angajati a ON v.AngajatID = a.AngajatID 
        GROUP BY a.Nume
    """, nativeQuery = true)
    List<Object[]> findTopAngajati();

    // --- JOIN 3: Clienti si Orasul lor (JOIN Vanzari-Clienti) - Doar pt afisare ---
    @Query(value = "SELECT c.Adresa, COUNT(*) FROM Vanzari v JOIN Clienti c ON v.ClientID = c.ClientID GROUP BY c.Adresa", nativeQuery = true)
    List<Object[]> findVanzariPerLocatie();
}