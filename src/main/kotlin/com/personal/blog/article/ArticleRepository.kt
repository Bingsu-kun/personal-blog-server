package com.personal.blog

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ArticleRepository : MongoRepository<Article, String> {

    @Query("{title: '/?0/i'}", sort = "{id: -1}") fun findByTitle(title: String): Iterable<Article>

    @Query(value = "{tags: {\$eleMatch: '?0'}}", sort = "{id: -1}")
    fun findByTag(tag: String): Iterable<Article>

    @Query(sort = "{id: -1}") fun findAllReverse(): Iterable<Article>
}
