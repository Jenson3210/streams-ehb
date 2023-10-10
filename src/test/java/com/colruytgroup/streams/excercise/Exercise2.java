package com.colruytgroup.streams.excercise;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise2 {

    @Test
    void generateSortedPendingPaymentsPerCustomer() {
        // TODO: prefetch all customers and put them in a map by id using name + ' ' + lastName as value
//        Map<Integer, String> customers = CustomerConstants.ALL_CUSTOMERS;
        // TODO: calculate total, sortByTotal and fetch corresponding name from customers map
        Map<String, Double> orders = new HashMap<>();
//        Map<String, Double> orders = OrderConstants.ALL_ORDERS;

        assertThat(orders).containsExactly(
                Map.entry("Jef Colruyt", 8.14),
                Map.entry("Jente Sondervorst", 6.64)
        );
    }
}
