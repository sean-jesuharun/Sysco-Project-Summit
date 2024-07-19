package org.sps.cart_microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_t")
@DynamicUpdate
@ToString
public class Cart {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_generator"
    )
    @GenericGenerator(
            name = "card_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "card_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "4"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    @Basic
    @Column(name = "total_price")
    private Double totalPrice;

    @Basic
    @Column(name = "last_update_time", nullable = false)
    private OffsetDateTime lastUpdateTime;


    public OffsetDateTime getCreationTime() {
        // Need to retrieve based on user TimeZone have to get it from User (ZoneId.of("Asia/Colombo"))
        return creationTime.atZoneSameInstant(ZoneId.systemDefault()).toOffsetDateTime();
    }

    public OffsetDateTime getLastUpdateTime() {
        // Need to retrieve based on user TimeZone have to get it from User (ZoneId.of("Asia/Colombo"))
        return lastUpdateTime.atZoneSameInstant(ZoneId.systemDefault()).toOffsetDateTime();
    }

//    public ZonedDateTime getCreationTime() {
//        return creationTime.atZoneSameInstant(ZoneId.systemDefault());
//    }
//
//    public void setCreationTime(ZonedDateTime creationTime) {
//        this.creationTime = creationTime.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
//    }
//
//    public ZonedDateTime getLastUpdateTime() {
//        return lastUpdateTime.atZoneSameInstant(ZoneId.systemDefault());
//    }
//
//    public void setLastUpdateTime(ZonedDateTime lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
//    }

}
