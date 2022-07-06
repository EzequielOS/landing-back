package br.edu.iftm.imbMary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Campo Obrigatorio: Nome")
    private String nome;
    @NotBlank(message = "Campo Obrigatorio: Telefone")
    private String telefone;

    private String email;
    private String mensagem;

}
