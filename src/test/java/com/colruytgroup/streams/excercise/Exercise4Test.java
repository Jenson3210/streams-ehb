package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.domain.orders.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;

class Exercise4Test {

    @Test
    void prepareReceiptsOfPaidItems() {
        // TODO fixme
        List<Order> orders = ALL_ORDERS;
        String receiptHeader = "FIRST_NAME;LAST_NAME;ARTICLE_COUNT;TOTAL_PRICE\n";
        String receiptLines = "ARTICLE_NAME;QUANTITY;PIECES_PRICE;TOTAL_PRICE\n";
    }
}
