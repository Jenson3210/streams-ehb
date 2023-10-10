package com.colruytgroup.streams.demo;

import com.colruytgroup.streams.data.ArticleConstants;
import com.colruytgroup.streams.domain.articles.Article;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.colruytgroup.streams.data.ArticleConstants.ALL_ARTICLES;

class StreamInitializationTest {

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

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void streamBuilder() {
        //make a stream
        Stream<Article> stream = Stream.<Article>builder()
                .add(ArticleConstants.BEER)
                .add(ArticleConstants.COFFEE_BEANS)
                .add(ArticleConstants.PIZZA)
                .build();

        //print the stream
        stream.forEach(System.out::println);
    }

    @Test
    void wholeNumberStreamFromRange() {
        //make the stream
        IntStream intStream = IntStream.range(1, 10);
        LongStream longStream = LongStream.range(1, 10);

        //print the stream
        intStream.forEach(System.out::println);
        longStream.forEach(System.out::println);
    }

    @Test
    void wholeNumberStreamFromRangeClosed() {
        //make the stream
        IntStream intStream = IntStream.rangeClosed(1, 10);
        LongStream longStream = LongStream.rangeClosed(1, 10);

        //print the stream
        intStream.forEach(System.out::println);
        longStream.forEach(System.out::println);
    }

    //TODO null safe method receiving a list of articles and returning a stream of articles

    private List<Article> getArticles() {
        return null;
    }
}
