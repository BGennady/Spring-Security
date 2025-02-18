package ru.netology.Spring.Security.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ORDERS", schema = "hibernate")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int numOrder;

    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "person_name", referencedColumnName = "name", nullable = false),
            @JoinColumn(name = "person_surname", referencedColumnName = "surname", nullable = false),
            @JoinColumn(name = "person_age", referencedColumnName = "age", nullable = false)
    })
    private Person person;
}