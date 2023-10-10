package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.articles.Article;
import com.colruytgroup.streams.domain.orders.Order;
import com.colruytgroup.streams.domain.orders.OrderLine;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static com.colruytgroup.streams.utils.TestingUtils.timeMethodExecution;

class IntermediateActionTest {

    @Test
    void ActionCanBeStatementLambda() {
        List<Order> orders = ALL_ORDERS;

        orders.stream()
                .filter(order -> {
                    if (order.orderLines().size() > 3)
                        return true;
                    else {
                        return false;
                    }
                })
                .forEach(System.out::println);
    }

    @Test
    void ActionCanBeExpressionLambda() {
        ALL_ORDERS.stream()
                .filter(order -> order.paid())
                .forEach(System.out::println);
    }

    @Test
    void ActionCanBeMethodReference() {
        ALL_ORDERS.stream()
                .filter(Order::paid)
                .forEach(System.out::println);
    }

    @Test
    void ActionCanBeMethod() {
        ALL_ORDERS.stream()
                .map(getFirstArticleId())
                .forEach(System.out::println);
    }

    private Function<Order, Integer> getFirstArticleId() {
        return order -> order.orderLines().get(0).articleId();
    }

    @Test
    void ActionsCanBeChained() {
        ALL_ORDERS.stream()
                .filter(Order::paid)
                .map(Order::orderLines)
                .flatMap(Collection::stream)
                .map(OrderLine::articleId)
                .map(ArticleConstants::getArticle)
                .map(Article::name)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void theOrderOfChainingMatters() {
        Runnable distinctLast = () -> ALL_ORDERS.stream()
                .filter(Order::paid)
                .map(Order::orderLines)
                .flatMap(Collection::stream)
                .map(OrderLine::articleId)
                .map(ArticleConstants::getArticle)
                .map(Article::name)
                .distinct()
                .reduce((s, s2) -> "");

        Runnable distinctFirst = () -> ALL_ORDERS.stream()
                .filter(Order::paid)
                .map(Order::orderLines)
                .flatMap(Collection::stream)
                .map(OrderLine::articleId)
                .distinct()
                .map(ArticleConstants::getArticle)
                .map(Article::name)
                .reduce((s, s2) -> "");

        System.out.println("distinctLast: " + timeMethodExecution(distinctLast, 10000) + " ms");
        System.out.println("distinctFirst: " + timeMethodExecution(distinctFirst, 10000) + " ms");
    }



    @Test
    void theMethodsYouChainMatter() {
        ALL_ORDERS.stream()
                .filter(Order::paid)
                .map(Order::orderLines)
                .flatMap(Collection::stream)
                .mapToInt(OrderLine::articleId)
                .boxed()
                .reduce((s, s2) -> 0);
    }
}
