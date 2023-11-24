package com.personal.blog

import ReplaceArticleDto
import UpdateArticleDto
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Service

@EnableMongoRepositories
@Service
class ArticleService(private val repository: ArticleRepository) {

    fun findById(id: String): Article? = repository.findById(id).get()

    fun findAll(): Iterable<Article> = repository.findAllReverse()

    fun findByTag(tag: String): Iterable<Article> = repository.findByTag(tag)

    fun findByTitle(title: String): Iterable<Article> = repository.findByTitle(title)

    fun findByTagAndTitle(tag: String, title: String): Iterable<Article> =
            repository.findByTagAndTitle(tag, title)

    fun create(article: Article): Article = repository.save(article)

    fun createMany(articles: Iterable<Article>): Iterable<Article> = repository.saveAll(articles)

    fun update(id: String, article: UpdateArticleDto): Article? {
        if (!repository.existsById(id)) return null
        var before = repository.findById(id).get()

        if (!article.title.isNullOrBlank()) before.title = article.title
        if (!article.content.isNullOrBlank()) before.content = article.content
        if (!article.tags.isNullOrEmpty()) before.tags = article.tags
        if (!article.thumbnail.isNullOrBlank()) before.thumbnail = article.thumbnail

        return repository.save(before)
    }

    fun replace(id: String, article: ReplaceArticleDto): Article? {
        if (!repository.existsById(id)) return null
        var before = repository.findById(id).get()

        before.title = article.title
        before.content = article.content
        before.tags = article.tags
        before.thumbnail = article.thumbnail

        return repository.save(before)
    }

    fun delete(id: String): Unit = repository.deleteById(id)
}
