package com.agenda.pessoas.agenda.tables;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "agenda")
public class Agenda {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horario")
    private Date horario;

    @ManyToMany
    @Column(name = "id_usuario", nullable = false, length = 150)
    private Usuarios id_usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_disponivel")
    private Date data_disponivel;
}
