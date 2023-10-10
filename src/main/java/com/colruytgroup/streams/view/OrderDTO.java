package com.colruytgroup.streams.view;

import com.colruytgroup.streams.domain.customer.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record OrderDTO(CustomerDTO customer, List<OrderLineDTO> orderLines) {
    public OrderDTO(Customer customer) {
        this(new CustomerDTO(customer), new ArrayList<>());
    }

    public void addOrderLine(OrderLineDTO orderLine) {
        orderLines.add(orderLine);
    }

    @Override
    public String toString() {
        return "{" +
                "'customer': '" + customer +
                "', 'orderLines': '[" + orderLines.stream().map(Objects::toString).collect(Collectors.joining(", ")) +
                "]', 'totalPrice': '" + orderLines.stream().mapToDouble(OrderLineDTO::price).sum() +
                "'}";
    }
}
