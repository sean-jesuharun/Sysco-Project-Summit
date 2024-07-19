package org.sps.order_microservice.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    @Basic
    @Column(name = "street", nullable = false, columnDefinition = "varchar(50)")
    private String street;

    @Basic
    @Column(name = "city", nullable = false, columnDefinition = "varchar(50)")
    private String city;

    @Basic
    @Column(name = "postCode", nullable = false, columnDefinition = "varchar(50)")
    private String postCode;

    @Basic
    @Column(name = "country", nullable = false, columnDefinition = "varchar(50)")
    private String country;

}
