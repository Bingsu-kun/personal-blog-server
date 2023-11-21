package com.personal.blog

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BaseController {

    @GetMapping("/hc")
    fun healthCheck(): String {
        return "OK"
    }
}
