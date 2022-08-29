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
@Table(name = "user_statuses")
public class UserStatus {

    @Id
    @Column(name = "user_status_id",nullable = false)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "name",nullable = false)
    private String name;
}
