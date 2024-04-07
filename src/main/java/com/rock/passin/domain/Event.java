package com.rock.passin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, unique = true)
    private String slugs;

    @Column(nullable = false)
    private Integer maximum_attendess;

}
