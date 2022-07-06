package br.edu.iftm.imbMary.domain;
import javax.persistence.*;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String pass;

}
