package com.personal.blog

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/article")
class ArticleController(private val service: ArticleService) {

    @GetMapping("/{id}")
    fun getArticle(id: String): ArticleProperty {
        val nullableArticle = service.findById(id)

        if (nullableArticle.isEmpty) {
            throw ResponseStatusException(NOT_FOUND, "Article not exists.")
        }
        val article = nullableArticle.get()

        return ArticleProperty(article.title, article.content, article.tags, article.thumbnail)
    }

    @GetMapping("/")
    fun getArticles(tag: String? = null, title: String? = null): Iterable<ArticleProperty> {
        var articleProperties: ArrayList<ArticleProperty> = ArrayList<ArticleProperty>()
        val articles: Iterable<Article>

        if (!tag.isNullOrEmpty()) {
            articles = service.findByTag(tag)
        } else if (!title.isNullOrEmpty()) {
            articles = service.findByTitle(title)
        } else {
            articles = service.findAll()
        }

        for (article in articles) {
            articleProperties.add(ArticleProperty(article.title, article.content, article.tags))
        }

        return articleProperties
    }

    //     @PostMapping("/")
    //     fun createArticle(body: RequestEntity<ArticleProperty
    // >) {}
}
