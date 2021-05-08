package ru.mirea.konnova.restaurant.dto;

import ru.mirea.konnova.restaurant.model.ElementOfOrder;


import java.util.Set;

public class OrderDTO {
    private boolean status;
    private float total;
    private Set<ElementOfOrder> elementOfOrderSet;
}

