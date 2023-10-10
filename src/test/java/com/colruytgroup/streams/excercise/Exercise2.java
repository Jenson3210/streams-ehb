package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise1 {

    @Test
    void ListOfAllCustomersWhereLastNameContainsE() {
        // TODO fixme
        List<Customer> customers = getCustomers();

        assertThat(customers).size().isEqualTo(15);
    }

    @Test
    void ListOfAllCustomersWhereLastNameContainsEAndNameContainsL() {
        // TODO fixme
        List<Customer> customers = getCustomers();

        assertThat(customers).size().isEqualTo(4);
    }

    @Test
    void ListOfAllCustomersWhereAnyFieldContainsO() {
        // TODO fixme
        List<Customer> customers = getCustomers();

        assertThat(customers).size().isEqualTo(18);
    }

    private List<Customer> getCustomers() {
        return List.of(
                new Customer(1, "Wilfred", "Barnett"),
                new Customer(2, "Hugo", "Schneider"),
                new Customer(3, "Jazmine", "Burch"),
                new Customer(4, "Danny", "Snow"),
                new Customer(5, "Diane", "Hewitt"),
                new Customer(6, "Daniella", "Woodward"),
                new Customer(7, "Autumn", "Bowman"),
                new Customer(8, "Tori", "Golden"),
                new Customer(9, "Dawson", "Sherman"),
                new Customer(10, "Solomon", "Calhoun"),
                new Customer(11, "Zaynah", "Melendez"),
                new Customer(12, "Danyal", "Ibarra"),
                new Customer(13, "Roosevelt", "Wall"),
                new Customer(14, "Lexie", "Oneill"),
                new Customer(15, "Kenny", "Snow"),
                new Customer(16, "Loui", "Lindsey"),
                new Customer(17, "Chaya", "Curtis"),
                new Customer(18, "Sumayyah", "Garrett"),
                new Customer(19, "Ricardo", "Mcconnell"),
                new Customer(20, "Maliha", "Wheeler"),
                new Customer(21, "Carla", "Blackburn"),
                new Customer(22, "Charis", "Rios"),
                new Customer(23, "Roman", "Chavez"),
                new Customer(24, "Yousuf", "Shelton"),
                new Customer(25, "Trystan", "Bonilla"),
                new Customer(26, "Colin", "Fowler"),
                new Customer(27, "Owen", "Black"),
                new Customer(28, "Elinor", "O'Quinn"),
                new Customer(29, "Bronte", "Holloway"),
                new Customer(30, "Kaleb", "Walker")
        );
    }
}
