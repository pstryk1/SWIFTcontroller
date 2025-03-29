package com.PatrykKusper.SWIFTcontroller

import org.apache.logging.log4j.util.Strings
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/test")
class TestController {
    @GetMapping
    fun testMethod():String = "Test String"
}