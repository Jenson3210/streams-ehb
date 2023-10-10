package com.colruytgroup.streams.utils;

import com.colruytgroup.streams.domain.orders.OrderLine;

import java.util.ArrayList;
import java.util.List;

import static com.colruytgroup.streams.utils.ArticleIdConstants.*;

public class OrderLineConstants {

    static List<OrderLine> order1() {
        ArrayList<OrderLine> orderlines = new ArrayList<>();

        orderlines.add(new OrderLine(COFFEE_BEANS, 1, 1));
        orderlines.add(new OrderLine(PIZZA, 2, 2));
        orderlines.add(new OrderLine(CANDY, 1, 4));

        return orderlines;
    }

    static List<OrderLine> order2() {
        ArrayList<OrderLine> orderlines = new ArrayList<>();

        orderlines.add(new OrderLine(PINEAPPLE, 1, 3.59));
        orderlines.add(new OrderLine(COFFEE_BEANS, 1, 1.30));
        orderlines.add(new OrderLine(DONUT, 1, 1.75));
        orderlines.add(new OrderLine(BEER, 1, 1.50));

        return orderlines;
    }

    static List<OrderLine> order3() {
        ArrayList<OrderLine> orderlines = new ArrayList<>();

        orderlines.add(new OrderLine(CHICKEN, 1, 3.59));
        orderlines.add(new OrderLine(APPLE, 1, 1.30));
        orderlines.add(new OrderLine(COFFEE_BEANS, 1, 1.75));

        return orderlines;
    }

    static List<OrderLine> order4() {
        ArrayList<OrderLine> orderlines = new ArrayList<>();

        orderlines.add(new OrderLine(TORTILLA, 1, 3.59));
        orderlines.add(new OrderLine(COFFEE_BEANS, 1, 1.75));
        orderlines.add(new OrderLine(CHICKEN, 1, 1.30));
        orderlines.add(new OrderLine(APPLE, 1, 1.50));

        return orderlines;
    }

    static List<OrderLine> order5() {
        ArrayList<OrderLine> orderlines = new ArrayList<>();

        orderlines.add(new OrderLine(ICE_CREAM, 1, 1.32));
        orderlines.add(new OrderLine(CANDY, 1, 3.59));
        orderlines.add(new OrderLine(COFFEE_BEANS, 1, 1.30));
        orderlines.add(new OrderLine(TORTILLA, 1, 1.75));

        return orderlines;
    }
}
