package com.personal.blog

import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.collections.List
import org.springframework.data.annotation.Id

@Entity
class Article(
        @Id @GeneratedValue val id: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var title: String,
        var content: String,
        var tags: List<String>,
        var thumbnail: String? = null,
        var viewCount: Int = 0
) {
    fun increaseViewCount() = this.viewCount++
}
