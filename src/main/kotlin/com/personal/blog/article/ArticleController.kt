package com.personal.blog

import ReplaceArticleDto
import UpdateArticleDto
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/article")
class ArticleController(private val service: ArticleService) {

    @GetMapping("/{id}")
    fun getArticle(@PathVariable("id") id: String): Article =
            service.findById(id) ?: throw ResponseStatusException(NOT_FOUND, "Article not exists.")

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

    @PostMapping("/") fun createArticle(@RequestBody article: Article) = service.create(article)

    @PostMapping("/batch")
    fun createManyArticles(@RequestBody articles: List<Article>) = service.createMany(articles)

    @PatchMapping("/{id}")
    fun updateArticle(@PathVariable("id") id: String, @RequestBody article: UpdateArticleDto) =
            service.update(id, article)

    @PutMapping("/{id}")
    fun replaceArticle(@PathVariable("id") id: String, @RequestBody article: ReplaceArticleDto) =
            service.replace(id, article)

    @DeleteMapping("/{id}") fun deleteArticle(id: String): Unit = service.delete(id)
}
