package com.colruytgroup.streams.data;

import com.colruytgroup.streams.domain.articles.Article;

import java.util.List;

public class ArticleConstants {

    public static final Article COFFEE_BEANS = new Article(1, "Coffee Beans");
    public static final Article PIZZA = new Article(2, "Pizza");
    public static final Article CANDY = new Article(3, "Candy");
    public static final Article PINEAPPLE = new Article(4, "Pineapple");
    public static final Article DONUT = new Article(5, "Donut");
    public static final Article BEER = new Article(6, "Beer");
    public static final Article CHICKEN = new Article(7, "Chicken");
    public static final Article APPLE = new Article(8, "Apple");
    public static final Article TORTILLA = new Article(9, "Tortilla");
    public static final Article ICE_CREAM = new Article(10, "Ice Cream");

    public static final List<Article> ALL_ARTICLES = List.of(
            COFFEE_BEANS,
            PIZZA,
            CANDY,
            PINEAPPLE,
            DONUT,
            BEER,
            CHICKEN,
            APPLE,
            TORTILLA,
            ICE_CREAM
    );

    public static Article getArticle(Integer articleId) {
        return ArticleConstants.ALL_ARTICLES.stream()
                .filter(article -> article.id() == articleId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Article with id " + articleId + " not found"));
    }
}
