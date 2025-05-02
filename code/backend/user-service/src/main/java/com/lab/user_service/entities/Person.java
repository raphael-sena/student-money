package com.lab.user_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cpf;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
//    private Conta conta;
//    private InstituicaoEnsino instituicaoEnsino;
}
