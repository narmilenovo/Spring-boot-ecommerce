package com.example.springBootEcommerce.service;

import com.example.springBootEcommerce.dto.Purchase;
import com.example.springBootEcommerce.dto.PurchaseResponse;
import com.example.springBootEcommerce.entity.Customer;
import com.example.springBootEcommerce.entity.Order;
import com.example.springBootEcommerce.entity.OrderItem;
import com.example.springBootEcommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutService {
    @Autowired
    private CustomerRepository customerRepository;


    public PurchaseResponse placeOrder(Purchase purchase)
    {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();

        String email = customer.getMail();

        Customer findMail = customerRepository.findByMail(email);

        if (findMail != null)
        {
            customer = findMail;
        }

        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
