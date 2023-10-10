package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.articles.Article;
import com.colruytgroup.streams.domain.customer.Customer;
import com.colruytgroup.streams.domain.orders.OrderLine;
import com.colruytgroup.streams.view.OrderDTO;
import com.colruytgroup.streams.view.OrderLineDTO;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.colruytgroup.streams.data.CustomerConstants.ALL_CUSTOMERS;
import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static com.colruytgroup.streams.data.OrderLineConstants.ALL_ORDER_LINES;
import static com.colruytgroup.streams.utils.TestingUtils.timeMethodExecution;

public class PitfallsTest {

    @Test
    void serviceCallsOrDatabaseTransactionsInAForLoopCanImpactPerformance() {
        Runnable fetchArticleInForLoop = () -> {
            for (OrderLine orderLine : ALL_ORDER_LINES) {
                OrderLineDTO ol = new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.getTotal());
                System.out.println(ol);
            }
        };

        Runnable fetchArticleFirst = () -> {
            Map<Integer, Article> articles = getArticles();
            for (OrderLine orderLine : ALL_ORDER_LINES) {
                OrderLineDTO ol = new OrderLineDTO(articles.get(orderLine.articleId()), orderLine.getTotal());
                System.out.println(ol);
            }
        };

        System.out.println("fetchArticleInForLoop: " + timeMethodExecution(fetchArticleInForLoop, 10) + " ms");
        System.out.println("fetchArticleFirst: " + timeMethodExecution(fetchArticleFirst, 10) + " ms");
    }

    @Test
    void serviceCallsOrDatabaseTransactionsInAStreamCanImpactPerformance() {
        Runnable fetchArticleInStream = () -> ALL_ORDER_LINES.stream()
                .map(orderLine -> new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.getTotal()))
                .map(Objects::toString)
                .forEach(System.out::println);

        Runnable fetchArticleFirst = () -> {
            Map<Integer, Article> articles = getArticles();
            ALL_ORDER_LINES.stream()
                    .map(orderLine -> new OrderLineDTO(articles.get(orderLine.articleId()), orderLine.getTotal()))
                    .map(Objects::toString)
                    .forEach(System.out::println);
        };

        System.out.println("fetchArticleInStream: " + timeMethodExecution(fetchArticleInStream, 10) + " ms");
        System.out.println("fetchArticleFirst: " + timeMethodExecution(fetchArticleFirst, 10) + " ms");
    }

    @Test
    void serviceCallsOrDatabaseTransactionsInAStreamCanDrasticallyImpactPerformance() {
        Runnable listOrders = () -> {
            ALL_ORDERS.stream()
                    .map(order -> {
                        OrderDTO dto = new OrderDTO(getCustomer(order.customerId()));
                        order.orderLines().forEach(orderLine -> {
                            dto.addOrderLine(new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.getTotal()));
                        });
                        return dto;
                    })
                    .map(Objects::toString)
                    .forEach(System.out::println);
        };

        System.out.println("listOrders: " + timeMethodExecution(listOrders, 10) + " ms");
    }

    private Article getArticle(int articleId) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ArticleConstants.getArticle(articleId);
    }

    private Map<Integer, Article> getArticles() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ArticleConstants.ALL_ARTICLES.stream()
                .collect(Collectors.toMap(Article::id, Function.identity()));
    }

    private Customer getCustomer(int customerId) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ALL_CUSTOMERS.stream().filter(c -> c.id() == customerId).findFirst().orElseThrow();
    }
}
