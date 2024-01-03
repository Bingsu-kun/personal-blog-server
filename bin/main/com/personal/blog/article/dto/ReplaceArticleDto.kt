data class ReplaceArticleDto(
        val title: String,
        val content: String,
        val tags: List<String>,
        val thumbnail: String?
)
