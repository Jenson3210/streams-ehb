package com.colruytgroup.streams.excercise;

import com.colruytgroup.streams.domain.articles.Article;
import com.colruytgroup.streams.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.colruytgroup.streams.data.ArticleConstants.ALL_ARTICLES;
import static com.colruytgroup.streams.data.CustomerConstants.ALL_CUSTOMERS;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise2Test {

    @Test
    void returnAllCustomerNamesAndArticles() {
        // TODO fixme
        List<Customer> customers = ALL_CUSTOMERS;
        List<Article> articles = ALL_ARTICLES;
        List<String> customerNamesAndArticles = null;

        assertThat(customerNamesAndArticles)
                .contains("Jef Colruyt", "Coffee Beans", "Jente Sondervorst", "Pizza", "Danny Snow", "Candy")
                .size().isEqualTo(44);
    }
}
