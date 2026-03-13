/** Clasa pentru Client
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Clienti")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID")
    private Integer id;

    @Column(name = "Nume") private String nume;
    @Column(name = "Prenume") private String prenume;
    @Column(name = "CNP") private String cnp;
    @Column(name = "Telefon") private String telefon;
    @Column(name = "Email") private String email;
    @Column(name = "Adresa") private String adresa;

    // Getteri si Setteri (Genereaza-i cu Alt+Insert -> Getter and Setter -> Select All)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }

    // Metoda ajutatoare pentru afisare in dropdown
    public String getNumeComplet() { return nume + " " + prenume + " (" + cnp + ")"; }
}