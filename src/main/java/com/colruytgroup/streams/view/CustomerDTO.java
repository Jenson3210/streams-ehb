package com.colruytgroup.streams.view;

import com.colruytgroup.streams.domain.customer.Customer;

public record CustomerDTO(String name) {
    CustomerDTO(Customer customer) {
        this(customer.name() + " " + customer.lastName());
    }

    @Override
    public String toString() {
        return name;
    }
}
