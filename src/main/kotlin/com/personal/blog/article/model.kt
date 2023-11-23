package com.personal.blog

import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.Id

@Entity
class Article(
                @Id @GeneratedValue val id: Long? = null,
                val createdAt: LocalDateTime = LocalDateTime.now(),
                var title: String,
                var content: String,
                var tags: Array<String>,
                var viewCount: Int = 0
)
