package ru.mirea.konnova.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "total")
    private float total;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<ElementOfOrder> elementOfOrderSet;
}
