package com.personal.blog

import java.util.Optional
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Service

@EnableMongoRepositories
@Service
class ArticleService(private val repository: ArticleRepository) {

    fun findById(id: String): Optional<Article> = repository.findById(id)

    fun findAll(): Iterable<Article> = repository.findAll()

    fun findByTag(tag: String): Iterable<Article> = repository.findByTag(tag)

    fun findByTitle(title: String): Iterable<Article> = repository.findByTitle(title)

    fun create(article: ArticleProperty): Article {
        return repository.save(
                Article(null, article.title, article.content, article.tags, article.thumbnail)
        )
    }

    fun createMany(articles: Iterable<ArticleProperty>): Iterable<Article> {
        var result: MutableList<Article> = mutableListOf<Article>()
        for (a in articles) result.add(
                repository.save(Article(null, a.title, a.content, a.tags, a.thumbnail))
        )
        return result
    }

    // fun update(id: String): Optional<Article> {
    //     TODO update logic
    // }

    fun delete(id: String): Unit {
        repository.deleteById(id)
    }
}
