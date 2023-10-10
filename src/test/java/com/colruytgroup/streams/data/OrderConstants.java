package com.colruytgroup.streams.data;

import com.colruytgroup.streams.domain.orders.Order;

import java.util.List;

import static com.colruytgroup.streams.data.OrderLineConstants.*;

public class OrderConstants {

    public static final Order ORDER_1 = new Order(1, CustomerIdConstants.JOHN_DOE, true, order1());
    public static final Order ORDER_2 = new Order(2, CustomerIdConstants.JANE_DOE, true, order2());
    public static final Order ORDER_3 = new Order(3, CustomerIdConstants.JENTE_SONDERVORST, false, order3());
    public static final Order ORDER_4 = new Order(4, CustomerIdConstants.JEF_COLRUYT, false, order4());
    public static final Order ORDER_5 = new Order(5, CustomerIdConstants.JOHN_DOE, true, order5());

    public static final List<Order> ALL_ORDERS = List.of(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5);
}
