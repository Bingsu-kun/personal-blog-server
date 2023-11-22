package com.personal.blog

import java.time.LocalDateTime
import org.springframework.data.annotation.Id

class Article(
    @Id val id: Long? = null,
    var title: String,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var tags: Array<String>,
)