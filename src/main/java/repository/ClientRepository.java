package com.dealership.proiect.repository;

import com.dealership.proiect.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM Clienti", nativeQuery = true)
    List<Client> findAllNative();

    // INSERT CLIENT (SQL PUR)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Clienti (Nume, Prenume, CNP, Telefon, Email, Adresa) " +
            "VALUES (:nume, :prenume, :cnp, :tel, :email, :adresa)", nativeQuery = true)
    void insertClientNative(@Param("nume") String nume, @Param("prenume") String prenume,
                            @Param("cnp") String cnp, @Param("tel") String tel,
                            @Param("email") String email, @Param("adresa") String adresa);

    // Ne trebuie asta ca sa luam ID-ul clientului nou creat (pentru vanzare)
    @Query(value = "SELECT TOP 1 * FROM Clienti WHERE CNP = :cnp ORDER BY ClientID DESC", nativeQuery = true)
    Client findByCnpNative(@Param("cnp") String cnp);
}