/** Clasa pentru Dotari
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Dotari")
public class Dotare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DotareID")
    private Integer id;

    @Column(name = "Denumire")
    private String denumire;

    @Column(name = "PretExtra")
    private Double pretExtra;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public Double getPretExtra() { return pretExtra; }
    public void setPretExtra(Double pretExtra) { this.pretExtra = pretExtra; }
}