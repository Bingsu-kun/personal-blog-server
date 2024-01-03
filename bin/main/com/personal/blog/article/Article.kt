package com.personal.blog

import java.time.LocalDateTime
import kotlin.collections.List
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("articles")
data class Article(
        @Id val id: String? = null,
        var title: String,
        var content: String,
        var tags: List<String>,
        var thumbnail: String? = null,
) {
    val createdAt: LocalDateTime = LocalDateTime.now()

    var viewCount: Int = 0
        private set

    fun increaseViewCount() = this.viewCount++
}
