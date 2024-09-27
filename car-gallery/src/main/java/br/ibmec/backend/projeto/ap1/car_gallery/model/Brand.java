package br.ibmec.backend.projeto.ap1.car_gallery.model;

import java.util.List;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
@Entity(name="brand")
public class Brand {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "O campo de nome do fabricante não pode ser vazio!")
    private String name;

    @Column
    @NotEmpty(message = "O campo de país do fabricante não pode ser vazio!")
    private String country;

    @Column
    @NotEmpty(message = "O campo de nome do fundador do fabricante não pode ser vazio!")
    private String founder;

    @Column
    @NotNull(message = "O campo de data de criação do fabricante não pode ser vazio!")
    private String dateCreation;

    @Column
    @NotEmpty(message = "O campo de descrição do fabricante não pode ser vazio!")
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id", name = "brand_id")
    private List<Vehicle> vehicles;
}
