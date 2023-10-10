package com.colruytgroup.streams.domain.orders;

import java.util.List;

public record Order(int id, int customerId, boolean paid, List<OrderLine> orderLines) {
    public double getTotalNoStream() {
        double price = 0d;
        for (OrderLine orderLine : orderLines) {
            price += orderLine.getTotal();
        }
        return price;
    }

    public double getTotal() {
        return orderLines.stream()
                .mapToDouble(OrderLine::getTotal)
                .sum();

    }
}
