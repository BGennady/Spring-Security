package ru.netology.Spring.Security.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PERSONS", schema = "hibernate")

public class Person {

    @EmbeddedId
    private PersonID personID;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String cityOfLiving;

    @OneToMany (mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
