package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.domain.orders.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise4Test {

    @Test
    void prepareReceiptsOfPaidItems() {
        List<Order> orders = ALL_ORDERS;
        String receiptHeader = "FIRST_NAME;LAST_NAME;ARTICLE_COUNT;TOTAL_PRICE\n";
        String receiptLines = "ARTICLE_NAME;QUANTITY;PIECES_PRICE;TOTAL_PRICE\n";

        // TODO fixme
        receiptHeader += "";
        receiptLines += "";

        assertThat(receiptHeader).isEqualTo("FIRST_NAME;LAST_NAME;ARTICLE_COUNT;TOTAL_PRICE\nJohn;Doe;3;9.0\nJane;Doe;4;8.14\nJohn;Doe;4;7.96");
        assertThat(receiptLines).isEqualTo("ARTICLE_NAME;QUANTITY;PIECES_PRICE;TOTAL_PRICE\nCoffee Beans;1;1.0;1.0\nPizza;2;2.0;4.0\nCandy;1;4.0;4.0\nPineapple;1;3.59;3.59\nCoffee Beans;1;1.3;1.3\nDonut;1;1.75;1.75\nBeer;1;1.5;1.5\nIce Cream;1;1.32;1.32\nCandy;1;3.59;3.59\nCoffee Beans;1;1.3;1.3\nTortilla;1;1.75;1.75");
    }
}
