package com.personal.blog

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : MongoRepository<Article, String> {

    @Query(value = "{tags: {\$eleMatch: '?0'}}", sort = "{id: -1}")
    fun findByTag(tag: String): Iterable<Article>

    @Query("{title: '/?0/i'}", sort = "{id: -1}") fun findByTitle(title: String): Iterable<Article>

    @Query("{title: '/?0/i', tags: {\$eleMatch: '?1'}}", sort = "{id: -1}")
    fun findByTagAndTitle(tag: String, title: String): Iterable<Article>
}
