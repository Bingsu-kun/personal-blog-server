package com.personal.blog

import BlogExceptions
import ExceptionCodes
import ReplaceArticleDto
import UpdateArticleDto
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController(private val service: ArticleService) {

    @GetMapping("/{id}")
    fun getArticle(@PathVariable("id") id: String): Article =
            service.findById(id) ?: throw BlogExceptions(ExceptionCodes.ARTICLE_NOT_FOUND)

    @GetMapping("/")
    fun getArticles(
            @RequestParam tag: String? = null,
            @RequestParam title: String? = null
    ): Iterable<Article> {

        if (!tag.isNullOrBlank() && !title.isNullOrBlank()) {
            return service.findByTagAndTitle(tag, title)
        } else if (!tag.isNullOrBlank()) {
            return service.findByTag(tag)
        } else if (!title.isNullOrBlank()) {
            return service.findByTitle(title)
        } else {
            return service.findAll()
        }
    }

    @PostMapping("/")
    fun createArticle(@RequestBody article: Article): Article = service.create(article)

    @PostMapping("/batch")
    fun createManyArticles(@RequestBody articles: List<Article>): Iterable<Article> =
            service.createMany(articles)

    @PatchMapping("/{id}")
    fun updateArticle(
            @PathVariable("id") id: String,
            @RequestBody article: UpdateArticleDto
    ): Article =
            service.update(id, article) ?: throw BlogExceptions(ExceptionCodes.ARTICLE_NOT_FOUND)

    @PutMapping("/{id}")
    fun replaceArticle(
            @PathVariable("id") id: String,
            @RequestBody article: ReplaceArticleDto
    ): Article =
            service.replace(id, article) ?: throw BlogExceptions(ExceptionCodes.ARTICLE_NOT_FOUND)

    @DeleteMapping("/{id}") fun deleteArticle(id: String): Unit = service.delete(id)
}
