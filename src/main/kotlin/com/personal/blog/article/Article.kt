package com.personal.blog

import java.time.LocalDateTime
import kotlin.collections.List
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("articles")
class Article(
        @Id private val id: ObjectId? = null,
        private var title: String,
        private var content: String,
        private var tags: List<String>,
        private var thumbnail: String? = null,
) {
    private val createdAt: LocalDateTime = LocalDateTime.now()

    private var viewCount: Int = 0
        private set

    fun increaseViewCount() = this.viewCount++
}
