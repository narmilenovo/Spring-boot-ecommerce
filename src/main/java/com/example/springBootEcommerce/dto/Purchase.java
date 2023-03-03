package com.example.springBootEcommerce.dto;


import com.example.springBootEcommerce.entity.Address;
import com.example.springBootEcommerce.entity.Customer;
import com.example.springBootEcommerce.entity.Order;
import com.example.springBootEcommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
