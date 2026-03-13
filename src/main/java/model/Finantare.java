/** Clasa pentru Finantare
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Finantari")
public class Finantare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FinantareID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "VanzareID")
    private Vanzare vanzare;

    @Column(name = "Banca") private String banca;
    @Column(name = "TipFinantare") private String tip;
    @Column(name = "PerioadaLuni") private Integer perioadaLuni;
    @Column(name = "Avans") private Double avans;

    // Getteri/Setteri
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Vanzare getVanzare() { return vanzare; }
    public void setVanzare(Vanzare vanzare) { this.vanzare = vanzare; }
    public String getBanca() { return banca; }
    public void setBanca(String banca) { this.banca = banca; }
    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public Integer getPerioadaLuni() { return perioadaLuni; }
    public void setPerioadaLuni(Integer perioadaLuni) { this.perioadaLuni = perioadaLuni; }
    public Double getAvans() { return avans; }
    public void setAvans(Double avans) { this.avans = avans; }
}