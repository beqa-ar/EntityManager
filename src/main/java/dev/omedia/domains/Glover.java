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
@Table(name = "glovers")
public class Glover {
    @Id
    @Column(name = "glover_id",nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "glover_id_seq")
    @SequenceGenerator(name = "glover_id_gen", sequenceName = "glover_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "glover_id",nullable = false)
    @OneToOne(targetEntity = TransportType.class)
    private  TransportType transportType;

    @Column(name = "name",nullable = false)
    private  String name;

    @Column(name = "job_start_date",nullable = false)
    private  LocalDate jobStartDate;
}
