package com.PatrykKusper.SWIFTcontroller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TestControllerTest {


    private val testGet = TestController()

    @Test
    fun testMethod() {

        val text = testGet.testMethod()

        kotlin.test.assertNotNull(text)


    }
}