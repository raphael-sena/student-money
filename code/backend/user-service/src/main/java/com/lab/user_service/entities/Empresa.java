package com.lab.user_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empresa extends Usuario {

    private String cnpj;
//    private List<Vantagem> vantagens;
}