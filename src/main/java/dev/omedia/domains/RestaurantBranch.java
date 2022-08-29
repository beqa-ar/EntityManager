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
@Table(name = "restaurant_branches",uniqueConstraints = {@UniqueConstraint(columnNames ={"restaurant_branch_id","restaurant_id"})})
public class RestaurantBranch {

    @Id
    @Column(name = "restaurant_branch_id", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "restaurant_branch_id_seq")
    @SequenceGenerator(name = "restaurant_branch_id_gen", sequenceName = "restaurant_branch_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "restaurant_id", nullable = false)
    private long restaurantId;

    @Column(name = "address", nullable = false)
    private  String address;

}
