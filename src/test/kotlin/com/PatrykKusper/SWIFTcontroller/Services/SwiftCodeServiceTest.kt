package com.PatrykKusper.SWIFTcontroller.Services

import com.PatrykKusper.SWIFTcontroller.model.SwiftCodeDataClass
import com.PatrykKusper.SWIFTcontroller.repository.SwiftRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
class SwiftCodeServiceTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var repository: SwiftRepository

    private lateinit var swiftCodeService: SwiftCodeService

    @BeforeEach
    fun setup() {
    swiftCodeService = SwiftCodeService(repository)
    }

    @Test
    fun `should return SwiftCodeDataClass when swift code exists`() {
        // Given
        val swiftCode = "ABCD1234"
        val swiftCodeEntity = SwiftCodeDataClass(
        swiftCode = swiftCode,
        bankName = "Test Bank",
        address = "Test Address",
        iso = "PL",
        countryName = "Polska",
        isHeadquarter = true
        )
        Mockito.`when`(repository.findBySwiftCode(swiftCode)).thenReturn(swiftCodeEntity)

        // When
        val result = swiftCodeService.getSwiftCode(swiftCode)

        // Then
        assertEquals(swiftCode, result.swiftCode)
        assertEquals("Test Bank", result.bankName)
        Mockito.verify(repository).findBySwiftCode(swiftCode)
    }

    @Test
    fun `should throw NotFoundException when swift code does not exist`() {
        // Given
        val swiftCode = "UNKNOWN"
        Mockito.`when`(repository.findBySwiftCode(swiftCode)).thenReturn(null)

        // When & Then
        val exception = assertThrows<NotFoundException> {
        swiftCodeService.getSwiftCode(swiftCode)
        }
        assertEquals("SWIFT code: UNKNOWN can not be found.", exception.message)
    }


    @Test
    fun `should return list of SwiftCodeDataClass when country iso exists`(){

    }

 @Test
 fun `should return country swift codes response when swift codes list is not empty`() {


     val countryISO2code = "PL"

     mockMvc.get("/v1/swift-codes/country/$countryISO2code:")
         .andDo { print() }
         .andExpect {
             status { isOk() }
             content { contentType(MediaType.APPLICATION_JSON) }
             jsonPath("$.countryISO2").value("PL")

         }
 }

 @Test
 fun `should throw NotFoundException when swift codes list is empty`() {
  // Given
  val countryISO2code = "XX"
  Mockito.`when`(swiftCodeService.getSwiftCodesByCountry(countryISO2code))
   .thenReturn(emptyList())

  // When & Then
  mockMvc.perform(get("/v1/swift-codes/country/$countryISO2code:"))
   .andExpect(status().isNotFound)
 }


}