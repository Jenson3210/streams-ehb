package com.colruytgroup.streams.view;

import com.colruytgroup.streams.domain.articles.Article;

public record ArticleDTO(String name) {
    ArticleDTO(Article article) {
        this(article.name());
    }

    @Override
    public String toString() {
        return name;
    }
}
