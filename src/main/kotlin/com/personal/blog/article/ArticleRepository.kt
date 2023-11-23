package com.personal.blog

import kotlin.collections.List;

import org.springframework.data.mongodb.repository.MongoRepository


interface ArticleRepository: MongoRepository<Article, String> {

    List<Article> findByTitle(String title)
    List<Article> findByTag(String Tag) 
}