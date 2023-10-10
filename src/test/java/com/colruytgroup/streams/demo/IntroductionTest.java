package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.domain.orders.Order;
import com.colruytgroup.streams.domain.orders.OrderLine;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.colruytgroup.streams.data.ArticleIdConstants.*;
import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static com.colruytgroup.streams.data.OrderConstants.ORDER_1;
import static org.assertj.core.api.Assertions.assertThat;

class IntroductionTest {

    @Test
    void givenOrderWithMultipleOrderLines_whenCalculatingTotalPrice_thenReturnsSumOfOrderLineTotals() {
        // When
        double totalPrice = ORDER_1.getTotalNoStream();
        // Then
        assertThat(totalPrice).isEqualTo(9);
    }

    @Test
    void givenOrderWithMultipleOrderLines_whenCalculatingTotalPriceWithStream_thenReturnsSumOfOrderLineTotals() {
        // When
        double totalPrice = ORDER_1.getTotal();

        // Then
        assertThat(totalPrice).isEqualTo(9);
    }

    @Test
    void givenOrders_whenUsingNoStreams_thenCalculatesOpenOrderCountPerArticleCorrectly() {
        // Given codebase
        Map<Integer, Integer> articleCounters = new HashMap<>();
        for (Order order : ALL_ORDERS) {
            if (order.paid()) {
                for (OrderLine orderLine : order.orderLines()) {
                    int article = orderLine.articleId();
                    Integer quantity = articleCounters.getOrDefault(orderLine.articleId(), 0);
                    quantity += orderLine.quantity();
                    articleCounters.put(article, quantity);
                }
            }
        }

        // Then
        assertions(articleCounters);
    }

    @Test
    void givenOrders_whenUsingStreams_thenCalculatesOpenOrderCountPerArticleCorrectly() {
        // Given codebase
        Map<Integer, Integer> articleCounters = ALL_ORDERS.stream()
                .filter(Order::paid)
                .map(Order::orderLines)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(OrderLine::articleId,
                        Collectors.summingInt(OrderLine::quantity)));

        // Then
        assertions(articleCounters);
    }

    private void assertions(Map<Integer, Integer> articleCounters) {
        assertThat(articleCounters)
                .containsEntry(COFFEE_BEANS, 3)
                .containsEntry(PIZZA, 2)
                .containsEntry(CANDY, 2)
                .containsEntry(PINEAPPLE, 1)
                .containsEntry(DONUT, 1)
                .containsEntry(BEER, 1)
                .containsEntry(ICE_CREAM, 1)
                .containsEntry(TORTILLA, 1);
    }
}
