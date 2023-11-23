package com.personal.blog

import java.util.Optional
import org.springframework.stereotype.Service

@Service
class ArticleService(private val repository: ArticleRepository) {

    fun findById(id: String): Optional<Article> = repository.findById(id)

    fun findAll(): Iterable<Article> = repository.findAll()

    fun findByTag(tag: String): Iterable<Article> = repository.findByTag(tag)

    fun findByTitle(title: String): Iterable<Article> = repository.findByTitle(title)

    fun create(article: Article): Article = repository.save(article)

    fun createMany(articles: Iterable<Article>): Iterable<Article> = repository.saveAll(articles)

    fun update(id: String): Optional<Article> {
        // TODO update logic
    }

    fun delete(id: String): Unit {
        repository.deleteById(id)
    }
}
