package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.data.OrderConstants;
import com.colruytgroup.streams.domain.articles.Article;
import com.colruytgroup.streams.domain.customer.Customer;
import com.colruytgroup.streams.domain.orders.Order;
import com.colruytgroup.streams.domain.orders.OrderLine;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.colruytgroup.streams.data.CustomerConstants.ALL_CUSTOMERS;
import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static com.colruytgroup.streams.data.OrderLineConstants.ALL_ORDER_LINES;

class TerminalActionTest {

    @Test
    void collectingIntoCollection() {
        List<Order> paidOrders = ALL_ORDERS.stream()
                .filter(Order::paid)
                .collect(Collectors.toList());
        paidOrders.add(OrderConstants.ORDER_3);

        Set<Article> articles = ALL_ORDER_LINES.stream()
                .map(OrderLine::articleId)
                .map(ArticleConstants::getArticle)
                .collect(Collectors.toSet());
        articles.add(ArticleConstants.CHICKEN);

        System.out.println("Paid Orders: " + paidOrders);
        System.out.println("Articles: " + articles);
    }

    @Test
    void collectingIntoImmutableCollections() {
        List<Order> paidOrders = ALL_ORDERS.stream()
                .filter(Order::paid)
                .collect(Collectors.toUnmodifiableList());

        Set<Article> articles = ALL_ORDER_LINES.stream()
                .map(OrderLine::articleId)
                .map(ArticleConstants::getArticle)
                .collect(Collectors.toUnmodifiableSet());

        System.out.println("Paid Orders: " + paidOrders);
        System.out.println("Articles: " + articles);
    }

    @Test
    void joiningIntoAString() {
        String pipeSeparatedNames = ALL_CUSTOMERS.stream()
                .map(Customer::name)
                .collect(Collectors.joining(""));

        System.out.println("customers: [" + pipeSeparatedNames + "]");
    }

    @Test
    void countingItems() {
        long jPeople = ALL_CUSTOMERS.stream()
                .filter(customer -> customer.name().startsWith("J"))
                .count();

        System.out.println("count: " + jPeople);
    }

    @Test
    void quickMath() {
        List<OrderLine> orderLines = ALL_ORDER_LINES;

        double totalRevenue = orderLines.stream()
                .mapToDouble(OrderLine::getTotal)
                .sum();

        double averageSalesPrice = orderLines.stream()
                .mapToDouble(OrderLine::getTotal)
                .average().orElse(0);

        double maxOrderLinePrice = orderLines.stream()
                .mapToDouble(OrderLine::getTotal)
                .max().orElse(0);

        double minOrderLinePrice = orderLines.stream()
                .mapToDouble(OrderLine::getTotal)
                .min().orElse(0);

        System.out.println("total: " + totalRevenue);
        System.out.println("average: " + averageSalesPrice);
        System.out.println("max: " + maxOrderLinePrice);
        System.out.println("min: " + minOrderLinePrice);
    }

    @Test
    void comparators() {
        List<OrderLine> orderLines = ALL_ORDER_LINES;

        double totalRevenue = orderLines.stream()
                .collect(Collectors.summingDouble(OrderLine::getTotal));

        double averageSalesPrice = orderLines.stream()
                .collect(Collectors.averagingDouble(OrderLine::getTotal));

        OrderLine maxOrderLineByPrice = orderLines.stream()
                .max(Comparator.comparingDouble(OrderLine::getTotal))
                .orElse(null);

        OrderLine minOrderLineByPrice = orderLines.stream()
                .min(Comparator.comparingDouble(OrderLine::getTotal))
                .orElse(null);

        System.out.println("total: " + totalRevenue);
        System.out.println("average: " + averageSalesPrice);
        System.out.println("max: " + maxOrderLineByPrice);
        System.out.println("min: " + minOrderLineByPrice);
    }

    @Test
    void theTerminalActionCanLimitTheIntermediateActionsExecutedWithinTheStream() {
        Integer orderId = ALL_ORDERS.stream()
                .peek(order -> System.out.println("Before filtering: " + order))
                .filter(order -> !order.paid())
                .peek(order -> System.out.println("After filtering: " + order))
                .map(Order::id)
                .findFirst()
                .orElse(-1);

        System.out.println("orderId: " + orderId);
    }
}
