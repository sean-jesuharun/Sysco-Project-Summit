package com.sps.product_microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_t")
@ToString
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @GenericGenerator(
            name = "product_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "4"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Basic
    @Column(name = "product_name", nullable = false, columnDefinition = "varchar(50)")
    private String productName;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @Basic
    @Column(name = "manufacture_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date manufactureDate;

    @Basic
    @Column(name = "expiry_dare", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

}
