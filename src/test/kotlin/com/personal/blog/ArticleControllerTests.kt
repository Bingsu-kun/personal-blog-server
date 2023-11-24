package com.personal.blog

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class ArticleControllerTests(
        @Autowired val mockMvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper
) {

    @MockBean lateinit var articleRepository: ArticleRepository

    @Test
    fun `Get article`() {
        // Given
        val simpleArticle =
                Article("65605d2055c8d990f6057163", "제목", "내용", listOf("Test"), "썸넬Url입니다")
        articleRepository.save(simpleArticle)

        // When
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/articles/65605d2055c8d990f6057163/")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].title").value(simpleArticle.title))
                .andExpect(jsonPath("\$.[0].content").value(simpleArticle.content))
                .andExpect(jsonPath("\$.[0].tags").value(simpleArticle.tags))
    }

    @Test
    fun `Get articles`() {
        // Given
        val simpleArticle1 = Article(null, "제목1", "내용1", listOf("Test"), "썸넬Url입니다")
        val simpleArticle2 = Article(null, "제목2", "내용2", listOf("Test"), "썸넬Url입니다")
        val simpleArticle3 = Article(null, "제목3", "내용3", listOf("Test"), "썸넬Url입니다")

        articleRepository.save(simpleArticle1)
        articleRepository.save(simpleArticle2)
        articleRepository.save(simpleArticle3)

        // When
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/articles/")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].title").value(simpleArticle1.title))
                .andExpect(jsonPath("\$.[1].title").value(simpleArticle2.title))
                .andExpect(jsonPath("\$.[2].title").value(simpleArticle3.title))
    }

    @Test
    fun `Create article`() {
        // Given
        val simpleArticle = Article(null, "제목", "내용", listOf("Test"), "썸넬Url입니다")

        // When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/articles/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(simpleArticle)) // JSON 화
                )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].title").value(simpleArticle.title))
                .andExpect(jsonPath("\$.[0].content").value(simpleArticle.content))
                .andExpect(jsonPath("\$.[0].tags").value(simpleArticle.tags))

        // Then
        val savedArticle = articleRepository.findAll().get(0)

        assert(simpleArticle.title == savedArticle.title)
        assert(simpleArticle.content == savedArticle.content)
        assert(simpleArticle.tags == savedArticle.tags)
        assert(simpleArticle.thumbnail == savedArticle.thumbnail)
    }
}
