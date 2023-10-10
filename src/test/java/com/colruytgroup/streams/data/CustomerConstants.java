package com.colruytgroup.streams.data;

import com.colruytgroup.streams.domain.customer.Customer;

import java.util.List;

public class CustomerConstants {

    public static final Customer JOHN_DOE = new Customer(1, "John", "Doe");
    public static final Customer JANE_DOE = new Customer(2, "Jane", "Doe");
    public static final Customer JENTE_SONDERVORST = new Customer(3, "Jente", "Sondervorst");
    public static final Customer JEF_COLRUYT = new Customer(4, "Jef", "Colruyt");

    public static final List<Customer> ALL_CUSTOMERS = getCustomers();

    private static List<Customer> getCustomers() {
        return List.of(
                JOHN_DOE,
                JANE_DOE,
                JENTE_SONDERVORST,
                JEF_COLRUYT,
                new Customer(101, "Wilfred", "Barnett"),
                new Customer(102, "Hugo", "Schneider"),
                new Customer(103, "Jazmine", "Burch"),
                new Customer(104, "Danny", "Snow"),
                new Customer(105, "Diane", "Hewitt"),
                new Customer(106, "Daniella", "Woodward"),
                new Customer(107, "Autumn", "Bowman"),
                new Customer(108, "Tori", "Golden"),
                new Customer(109, "Dawson", "Sherman"),
                new Customer(110, "Solomon", "Calhoun"),
                new Customer(111, "Zaynah", "Melendez"),
                new Customer(112, "Danyal", "Ibarra"),
                new Customer(113, "Roosevelt", "Wall"),
                new Customer(114, "Lexie", "Oneill"),
                new Customer(115, "Kenny", "Snow"),
                new Customer(116, "Loui", "Lindsey"),
                new Customer(117, "Chaya", "Curtis"),
                new Customer(118, "Sumayyah", "Garrett"),
                new Customer(119, "Ricardo", "Mcconnell"),
                new Customer(120, "Maliha", "Wheeler"),
                new Customer(121, "Carla", "Blackburn"),
                new Customer(122, "Charis", "Rios"),
                new Customer(123, "Roman", "Chavez"),
                new Customer(124, "Yousuf", "Shelton"),
                new Customer(125, "Trystan", "Bonilla"),
                new Customer(126, "Colin", "Fowler"),
                new Customer(127, "Owen", "Black"),
                new Customer(128, "Elinor", "O'Quinn"),
                new Customer(129, "Bronte", "Holloway"),
                new Customer(130, "Kaleb", "Walker")
        );
    }
}
