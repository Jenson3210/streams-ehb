package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.articles.Article;
import com.colruytgroup.streams.domain.customer.Customer;
import com.colruytgroup.streams.domain.orders.OrderLine;
import com.colruytgroup.streams.view.OrderDTO;
import com.colruytgroup.streams.view.OrderLineDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.colruytgroup.streams.data.CustomerConstants.ALL_CUSTOMERS;
import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static com.colruytgroup.streams.data.OrderLineConstants.ALL_ORDER_LINES;
import static com.colruytgroup.streams.utils.TestingUtils.timeMethodExecution;

class PitfallsTest {

    @Nested
    class ServiceCallsOrDatabaseTransactionsInStreamCanImpactPerformance {
        @Test
        void serviceCallsOrDatabaseTransactionsInAForLoopCanImpactPerformance() {
            Runnable fetchArticleInForLoop = () -> {
                for (OrderLine orderLine : ALL_ORDER_LINES) {
                    OrderLineDTO ol = new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.total());
                    System.out.println(ol);
                }
            };

            Runnable fetchArticleFirst = () -> {
                Map<Integer, Article> articles = getArticles();
                for (OrderLine orderLine : ALL_ORDER_LINES) {
                    OrderLineDTO ol = new OrderLineDTO(articles.get(orderLine.articleId()), orderLine.total());
                    System.out.println(ol);
                }
            };

            System.out.println("fetchArticleInForLoop: " + timeMethodExecution(fetchArticleInForLoop, 10) + " ms");
            System.out.println("fetchArticleFirst: " + timeMethodExecution(fetchArticleFirst, 10) + " ms");
        }

        @Test
        void serviceCallsOrDatabaseTransactionsInAStreamCanImpactPerformance() {
            Runnable fetchArticleInStream = () -> ALL_ORDER_LINES.stream()
                    .map(orderLine -> new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.total()))
                    .map(Objects::toString)
                    .forEach(System.out::println);

            Runnable fetchArticleFirst = () -> {
                Map<Integer, Article> articles = getArticles();
                ALL_ORDER_LINES.stream()
                        .map(orderLine -> new OrderLineDTO(articles.get(orderLine.articleId()), orderLine.total()))
                        .map(Objects::toString)
                        .forEach(System.out::println);
            };

            System.out.println("fetchArticleInStream: " + timeMethodExecution(fetchArticleInStream, 10) + " ms");
            System.out.println("fetchArticleFirst: " + timeMethodExecution(fetchArticleFirst, 10) + " ms");
        }

        @Test
        void serviceCallsOrDatabaseTransactionsInAStreamCanDrasticallyImpactPerformance() {
            //TODO Let's refactor this piece together
            Runnable listOrders = () -> {
                ALL_ORDERS.stream()
                        .map(order -> {
                            OrderDTO dto = new OrderDTO(getCustomer(order.customerId()));
                            order.orderLines().forEach(orderLine -> {
                                dto.addOrderLine(new OrderLineDTO(getArticle(orderLine.articleId()), orderLine.total()));
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

    @Nested
    class EndlessStreams {

        @Test
        void endlessStreamsPt1() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<Void> callable = () -> {
                IntStream.iterate(0, i -> (i + 1) % 2)
                        .forEach(System.out::println);
                return null;
            };

            boolean timedOut = false;
            try {
                executor.invokeAny(Collections.singleton(callable), 10, java.util.concurrent.TimeUnit.SECONDS);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                timedOut = true;
            }

            if (!timedOut) {
                throw new RuntimeException("This should not happen");
            }
        }

        @Test
        void endlessStreamsPt2() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<Void> callable = () -> {
                IntStream.iterate(0, i -> (i + 1) % 2)
                        .distinct()
                        .limit(10)
                        .forEach(System.out::println);
                return null;
            };

            boolean timedOut = false;
            try {
                executor.invokeAny(Collections.singleton(callable), 10, java.util.concurrent.TimeUnit.SECONDS);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                timedOut = true;
            }

            if (!timedOut) {
                throw new RuntimeException("This should not happen");
            }
        }
    }

    @Nested
    class TheOrderOfIntermediateActionsMatter {

        @Test
        void theOrderOfIntermediateActionsMatter() {
            IntStream.iterate(0, i -> i + 1)
                    .limit(10)
                    .skip(5)
                    .forEach(System.out::println);
        }

    }
}
