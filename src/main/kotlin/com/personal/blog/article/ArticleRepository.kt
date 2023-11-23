package com.personal.blog

import org.springframework.data.mongodb.repository.MongoRepository

interface ArticleRepository : MongoRepository<Article, String> {

    fun findByTitle(title: String): Iterable<Article>
    fun findByTag(tag: String): Iterable<Article>
}
