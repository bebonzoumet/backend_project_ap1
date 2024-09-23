package br.ibmec.backend.projeto.ap1.car_gallery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity(name="vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "O campo de modelo do veículo não pode ser vazio!")
    private String model;

    @Column
    @NotEmpty(message = "O campo de tipo do veículo não pode ser vazio!")
    private String type;

    @Column
    @NotEmpty(message = "O campo de potência do veículo não pode ser vazio!")
    private String power;

    @Column
    @NotEmpty(message = "O campo de nível de segurança do veículo não pode ser vazio!")
    private String safetyLevel;

    @Column
    @NotEmpty(message = "O campo de data de criação do veículo não pode ser vazio!")
    private String dateCreation;

    @Column
    @NotEmpty(message = "O campo de descrição do veículo não pode ser vazio!")
    private String description;

    @Column
    @NotEmpty(message = "O campo de imagem do veículo não pode ser vazio!")
    private String image;
}
