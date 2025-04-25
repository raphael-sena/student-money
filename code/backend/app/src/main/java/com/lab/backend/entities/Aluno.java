package com.lab.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno extends Pessoa {

    private String rg;
    private String curso;
}
