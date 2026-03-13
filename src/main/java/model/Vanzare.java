package com.dealership.proiect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Vanzari")
public class Vanzare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VanzareID")
    private Integer id;

    // AM SCOS @NotNull DE AICI (Verificam manual in Controller)
    @ManyToOne
    @JoinColumn(name = "MasinaID")
    private Masina masina;

    // AM SCOS @NotNull DE AICI (Verificam manual in Controller)
    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "AngajatID")
    private Angajat angajat;

    @Column(name = "DataVanzare") private LocalDate dataVanzare;

    @NotNull(message = "Preț final necesar")
    @Min(value = 1, message = "Preț invalid")
    @Column(name = "PretFinal") private Double pretFinal;

    @Column(name = "ModalitatePlata") private String modalitatePlata;

    // Getteri si Setteri
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Masina getMasina() { return masina; }
    public void setMasina(Masina masina) { this.masina = masina; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Angajat getAngajat() { return angajat; }
    public void setAngajat(Angajat angajat) { this.angajat = angajat; }
    public LocalDate getDataVanzare() { return dataVanzare; }
    public void setDataVanzare(LocalDate dataVanzare) { this.dataVanzare = dataVanzare; }
    public Double getPretFinal() { return pretFinal; }
    public void setPretFinal(Double pretFinal) { this.pretFinal = pretFinal; }
    public String getModalitatePlata() { return modalitatePlata; }
    public void setModalitatePlata(String modalitatePlata) { this.modalitatePlata = modalitatePlata; }
}