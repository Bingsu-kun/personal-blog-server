package com.personal.blog

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("article")
data class ArticleProperty(var title: String, var content: String, var tags: List<String> var thumbnail: String? = null) 