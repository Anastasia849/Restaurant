package ru.mirea.konnova.restaurant.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_elements")
public class ElementOfOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    ShoppingCart shoppingCart;

    public ElementOfOrder(Dish dish){
        this.name=dish.getName();
        this.price=dish.getPrice();
        this.description=dish.getDescription();
    }


}
