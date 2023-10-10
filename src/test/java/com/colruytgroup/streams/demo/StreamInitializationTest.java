package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.articles.Article;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.colruytgroup.streams.data.ArticleConstants.ALL_ARTICLES;

class StreamInitialization {

    @Test
    void usingStreamFunctionOnAList() {
        //make a list
        List<Article> articles = ALL_ARTICLES;

        //make a stream
        Stream<Article> stream = articles.stream();

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void canAlsoBeAnEmptyList() {
        //make a list
        List<Article> articles = Collections.emptyList();

        //make a stream
        Stream<Article> stream = articles.stream();

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void whyDoesThisNotWork() {
        //make a list
        List<Article> articles = getArticles();

        //make a stream
        Stream<Article> stream = articles.stream();

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void streamOf() {
        //make a list
        Stream<Article> stream = Stream.of(ArticleConstants.BEER, ArticleConstants.COFFEE_BEANS);

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void concatStreams() {
        //make a list
        Stream<Article> importantArticles = Stream.of(ArticleConstants.BEER, ArticleConstants.COFFEE_BEANS);
        Stream<Article> alsoYummy = Stream.of(ArticleConstants.PIZZA);

        //combine streams
        Stream<Article> stream = Stream.concat(importantArticles, alsoYummy);

        stream = Stream.concat(stream, emptyArticlesStream);

        //print the stream
        stream.forEach(System.out::println);

        List<Article> list = stream.toList();
        for (Article article : list) {
            System.out.println(article);
        }
    }

    private List<Article> getArticles() {
        return null;
    }
}
