package ru.beorg.test.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Vlad on 05.02.2017.
 */
@Data
@Entity
@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String name;
}
