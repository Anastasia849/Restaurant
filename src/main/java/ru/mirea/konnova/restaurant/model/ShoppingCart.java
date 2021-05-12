package ru.mirea.konnova.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "total")
    private float total;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ElementOfOrder> elementOfOrderSet;

    @ManyToOne
    private User user;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "shopping_cart_status", joinColumns = @JoinColumn(name = "shopping_cart_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> statuses;
}
