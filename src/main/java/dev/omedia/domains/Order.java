package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_gen", sequenceName = "order_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "glover_id", nullable = false)
    private long gloverId;

    @Column(name = "branch_id", nullable = false)
    private long branchId;

    @Column(name = "restaurant_id", nullable = false)
    private long restaurantId;

    @Column(name = "distance", nullable = false)
    private long distance;

    @Column(name = "price", nullable = false)
    private double price;
}
