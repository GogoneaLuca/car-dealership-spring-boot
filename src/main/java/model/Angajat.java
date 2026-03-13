/** Clasa pentru Angajat
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Angajati")
public class Angajat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AngajatID")
    private Integer id;

    @NotBlank(message = "Nume necesar")
    @Column(name = "Nume") private String nume;

    @NotBlank(message = "Prenume necesar")
    @Column(name = "Prenume") private String prenume;

    @NotBlank(message = "Email obligatoriu")
    @Email(message = "Format invalid")
    @Column(name = "Email") private String email;

    @Size(min = 4, message = "Minim 4 caractere")
    @Column(name = "Parola") private String parola;

    @Column(name = "Functie") private String functie;

    @Min(value = 1, message = "Salariu pozitiv")
    @Column(name = "Salariu") private Double salariu;

    @Column(name = "DataAngajare") private LocalDate dataAngajare;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }
    public String getFunctie() { return functie; }
    public void setFunctie(String functie) { this.functie = functie; }
    public Double getSalariu() { return salariu; }
    public void setSalariu(Double salariu) { this.salariu = salariu; }
    public LocalDate getDataAngajare() { return dataAngajare; }
    public void setDataAngajare(LocalDate dataAngajare) { this.dataAngajare = dataAngajare; }
}