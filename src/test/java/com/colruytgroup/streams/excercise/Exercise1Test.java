package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.colruytgroup.streams.data.CustomerConstants.ALL_CUSTOMERS;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise1Test {

    @Test
    void listOfAllCustomersWhereLastNameContainsE() {
        // TODO fixme
        List<Customer> customers = ALL_CUSTOMERS;

        assertThat(customers).size().isEqualTo(18);
    }

    @Test
    void listOfAllCustomersWhereLastNameContainsEAndNameContainsL() {
        // TODO fixme
        List<Customer> customers = ALL_CUSTOMERS;

        assertThat(customers).size().isEqualTo(6);
    }

    @Test
    void listOfAllCustomersWhereAnyFieldContainsO() {
        // TODO fixme
        List<Customer> customers = ALL_CUSTOMERS;

        assertThat(customers).size().isEqualTo(24);
    }
}
