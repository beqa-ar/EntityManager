package dev.omedia.domains;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "l_transport_types")
public class TransportType {

    @Id
    @Column(name = "transport_type_id",nullable = false)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "name",nullable = false)
    private  String name;
}
