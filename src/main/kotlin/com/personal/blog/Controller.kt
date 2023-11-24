package com.personal.blog

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class BaseController {

    @GetMapping("/hc")
    fun healthCheck(): String {
        return "OK"
    }
}
