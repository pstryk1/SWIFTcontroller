package com.PatrykKusper.SWIFTcontroller

import com.PatrykKusper.SWIFTcontroller.Services.SwiftCodeService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class SwiftCodeServiceTest {
    
    
    private val dataSource = SwiftCode(
        swiftCode = "TODO()",
        bankName = "TODO()",
        address = "TODO()",
        iso = "TODO()",
        countryName = "TODO()",
        isHeadquarter = false,
        branches = null
    )

    private val repository: SwiftRepository = mockk()


    private val swiftCodeService = SwiftCodeService(repository)

    @Test
    fun `should return swift code when found`() {
        val swiftCode = "TESTSWIFT"
        val expected = SwiftCode(
            swiftCode = swiftCode,
            bankName = "Test Bank",
            address = "Test Address",
            iso = "PL",
            countryName = "POLAND",
            isHeadquarter = true
        )

        every { repository.findBySwiftCode(swiftCode) } returns expected

        val result = swiftCodeService.getSwiftCode(swiftCode)

        assertEquals(expected, result)
    }

//    @Test
//    fun getSwiftCodesByCountry() {
//    }
//
//    @Test
//    fun addSwiftCode() {
//
//    }
//
//    @Test
//    fun deleteSwiftCode() {
//    }
//
//    @Test
//    fun assignBranchesToHeadquarters() {
//    }
//
//    @Test
//    fun getBranchesForHeadquarter() {
//    }
}