package ru.mirea.konnova.restaurant.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_elements")
@NoArgsConstructor
public class ElementOfOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private float price;

    @ManyToOne
    private Order order;

    public ElementOfOrder(Dish dish){
        name=dish.getName();
        price=dish.getPrice();
    }
}
