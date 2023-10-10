package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.orders.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.colruytgroup.streams.data.OrderConstants.ALL_ORDERS;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise3Test {

    @Test
    void returnAllArticleNamesInUnpaidOrders() {
        // TODO fixme
        List<Order> orders = ALL_ORDERS;
        List<String> articleNames = null;

        assertThat(articleNames)
                .containsOnlyOnce(ArticleConstants.CHICKEN.name(), ArticleConstants.APPLE.name(), ArticleConstants.COFFEE_BEANS.name(), ArticleConstants.TORTILLA.name());
    }
}
