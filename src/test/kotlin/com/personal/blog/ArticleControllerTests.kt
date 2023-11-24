package com.personal.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class ArticleControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockBean lateinit var articleRepository: ArticleRepository

    @Test
    fun `Get article`() {
        // Given
        val simpleArticle =
                Article("65605d2055c8d990f6057163", "제목", "내용", listOf("Test"), "썸넬Url입니다")
        articleRepository.save(simpleArticle)

        // When
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/articles/65605d2055c8d990f6057163")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].title").value(simpleArticle.title))
                .andExpect(jsonPath("\$.[0].content").value(simpleArticle.content))
                .andExpect(jsonPath("\$.[0].tags").value(simpleArticle.tags))
    }
}
