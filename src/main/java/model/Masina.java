/** Clasa pentru Masina
 * @author Gogonea Luca
 * @version 8 Ianuarie 2026
 */
package com.dealership.proiect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Masini")
public class Masina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MasinaID")
    private Integer id;

    @NotBlank(message = "Marca este obligatorie")
    @Column(name = "Marca")
    private String marca;

    @NotBlank(message = "Modelul este obligatoriu")
    @Column(name = "Model")
    private String model;

    @NotNull(message = "Completează anul")
    @Min(value = 1900, message = "Minim 1990")
    @Max(value = 2100, message = "Maxim anul 2100")
    @Column(name = "AnFabricatie")
    private Integer anFabricatie;

    @NotNull(message = "Prețul este obligatoriu")
    @Min(value = 1, message = "Prețul trebuie să fie pozitiv")
    @Column(name = "Pret")
    private Double pret;

    @NotNull(message = "Kilometrajul este obligatoriu") // UPDATE AICI
    @Min(value = 0, message = "Nu poate fi negativ")
    @Column(name = "Kilometraj")
    private Integer kilometraj;

    @NotBlank(message = "VIN lipsă")
    @Size(min = 3, max = 20, message = "VIN incorect")
    @Column(name = "NumarSasiu", unique = true)
    private String numarSasiu;

    @Column(name = "Combustibil") private String combustibil;
    @Column(name = "Culoare") private String culoare;
    @Column(name = "Stare") private String stare;
    @Column(name = "Disponibilitate") private String disponibilitate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MasiniDotari",
            joinColumns = @JoinColumn(name = "MasinaID"),
            inverseJoinColumns = @JoinColumn(name = "DotareID")
    )
    private List<Dotare> dotari = new ArrayList<>();

    // Getteri si Setteri
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Integer getAnFabricatie() { return anFabricatie; }
    public void setAnFabricatie(Integer anFabricatie) { this.anFabricatie = anFabricatie; }
    public Double getPret() { return pret; }
    public void setPret(Double pret) { this.pret = pret; }
    public Integer getKilometraj() { return kilometraj; }
    public void setKilometraj(Integer kilometraj) { this.kilometraj = kilometraj; }
    public String getNumarSasiu() { return numarSasiu; }
    public void setNumarSasiu(String numarSasiu) { this.numarSasiu = numarSasiu; }
    public String getCombustibil() { return combustibil; }
    public void setCombustibil(String combustibil) { this.combustibil = combustibil; }
    public String getCuloare() { return culoare; }
    public void setCuloare(String culoare) { this.culoare = culoare; }
    public String getStare() { return stare; }
    public void setStare(String stare) { this.stare = stare; }
    public String getDisponibilitate() { return disponibilitate; }
    public void setDisponibilitate(String disponibilitate) { this.disponibilitate = disponibilitate; }
    public List<Dotare> getDotari() { return dotari; }
    public void setDotari(List<Dotare> dotari) { this.dotari = dotari; }
}