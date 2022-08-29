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
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "status",nullable = false)
    private UserStatus status;

    @Column(name = "nick_name",nullable = false)
    private  String nickName;

    @Column(name = "password",nullable = false)
    private  String password;

    @Column(name = "balance",nullable = false)
    private  String balance;




}
