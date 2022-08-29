package dev.omedia.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "restaurant_id", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "restaurant_id_seq")
    @SequenceGenerator(name = "restaurant_id_gen", sequenceName = "restaurant_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "name", nullable = false)
    private  String name;

    @Column(name = "contract_start_date", nullable = false)
    private  LocalDate contractStartDate;

    @Column(name = "contract_end_date", nullable = false)
    private  LocalDate contractEndDate;
}
