package com.colruytgroup.streams.view;

import com.colruytgroup.streams.domain.articles.Article;

public record OrderLineDTO(ArticleDTO article, double price) {
    public OrderLineDTO(Article article, double price) {
        this(new ArticleDTO(article), price);
    }

    @Override
    public String toString() {
        return "{" +
                "'article': '" + article +
                "', 'price': '" + price +
                "'}";
    }
}
