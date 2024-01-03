import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

enum class ExceptionCodes(val status: HttpStatus, val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),
    FAILED_TO_SAVE_ARTICLE(HttpStatus.INTERNAL_SERVER_ERROR, "게시물 등록에 실패했습니다."),
    FAILED_TO_UPDATE_ARTICLE(HttpStatus.INTERNAL_SERVER_ERROR, "게시물 수정에 실패했습니다."),
    FAILED_TO_DELETE_ARTICLE(HttpStatus.INTERNAL_SERVER_ERROR, "게시물 삭제에 실패했습니다.")
}

class BlogExceptions(code: ExceptionCodes) : RuntimeException() {
    val code: ExceptionCodes = code
}

@RestControllerAdvice
class BlogExceptionHandler {
    @ExceptionHandler(BlogExceptions::class)
    protected fun handleBlogException(e: BlogExceptions): ResponseEntity<BlogExceptions> {
        return ResponseEntity.status(e.code.status).body(e)
    }
}
