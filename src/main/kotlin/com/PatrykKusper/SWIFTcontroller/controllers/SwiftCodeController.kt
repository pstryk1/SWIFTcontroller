package com.PatrykKusper.SWIFTcontroller.controllers

import com.PatrykKusper.SWIFTcontroller.Services.NotFoundException
import com.PatrykKusper.SWIFTcontroller.Services.SwiftCodeImportService
import com.PatrykKusper.SWIFTcontroller.Services.SwiftCodeService
import com.PatrykKusper.SWIFTcontroller.model.SwiftCodeDataClass
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/v1/swift-codes")
class SwiftCodeController(
    private val swiftCodeImportService: SwiftCodeImportService,
    private val swiftCodeService: SwiftCodeService,
) {
    @GetMapping("/{swiftCode}")
    fun getSwiftCode(@PathVariable swiftCode: String): ResponseEntity<Any> {
        val swiftCodeEntity = swiftCodeService.getSwiftCode(swiftCode)

        return if (swiftCodeEntity.isHeadquarter) {
            val branches = swiftCodeService.getBranchesForHeadquarter(swiftCodeEntity)
            val response = HeadquarterResponse(
                address = swiftCodeEntity.address,
                bankName = swiftCodeEntity.bankName,
                countryISO2 = swiftCodeEntity.iso,
                countryName = swiftCodeEntity.countryName,
                isHeadquarter = swiftCodeEntity.isHeadquarter,
                swiftCode = swiftCodeEntity.swiftCode,
                branches = branches.map { branch ->
                    SimpleBranchResponse(
                        address = branch.address,
                        bankName = branch.bankName,
                        countryISO2 = branch.iso,
                        isHeadquarter = branch.isHeadquarter,
                        swiftCode = branch.swiftCode
                    )
                }
            )
            ResponseEntity.ok(response)
        } else {

            val response = BranchResponse(
                address = swiftCodeEntity.address,
                bankName = swiftCodeEntity.bankName,
                countryISO2 = swiftCodeEntity.iso,
                countryName = swiftCodeEntity.countryName,
                isHeadquarter = swiftCodeEntity.isHeadquarter,
                swiftCode = swiftCodeEntity.swiftCode
            )
            ResponseEntity.ok(response)
        }
    }

    @GetMapping("country/{countryISO2code}:")
    fun getSwiftCodeByISO2(@PathVariable countryISO2code: String): ResponseEntity<Any> {
        val swiftCodes = swiftCodeService.getSwiftCodesByCountry(countryISO2code)
        if (swiftCodes.isEmpty()) {
            throw NotFoundException("Swift code can not be found for country code: $countryISO2code")
        }

        val response = CountrySwiftCodesResponse(
            countryISO2 = countryISO2code.uppercase(),
            countryName = swiftCodes.first().countryName,
            swiftCodes = swiftCodes.map {
                SwiftCodeResponse(
                    bankName = it.bankName,
                    countryISO2 = it.countryName,
                    isHeadquarter = it.isHeadquarter,
                    swiftCode = it.swiftCode
                )

            }
        )

        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun addSwiftCode(@RequestBody request: SwiftCodeDataClass): ResponseEntity<Map<String, String>> {
        swiftCodeService.addSwiftCode(request)
        return ResponseEntity.ok(mapOf("message" to "SWIFT code added successfully."))
    }

    @DeleteMapping("/{swiftCode}")
    fun deleteSwiftCode(@PathVariable swiftCode: String): ResponseEntity<Map<String, String>> {
        swiftCodeService.deleteSwiftCode(swiftCode)
        return ResponseEntity.ok(mapOf("message" to "SWIFT code deleted successfully."))
    }






    @PostMapping("/import")
    fun importCsv(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        file.inputStream.use { inputStream ->

            swiftCodeImportService.importFromCsv(inputStream)
        }
        return ResponseEntity.ok("Import zakończony sukcesem")
    }
}

data class HeadquarterResponse(
    val address: String,
    val bankName: String,
    val countryISO2: String,
    val countryName: String,
    val isHeadquarter: Boolean,
    val swiftCode: String,
    val branches: List<SimpleBranchResponse>
)



data class CountrySwiftCodesResponse(
    val countryISO2: String,
    val countryName: String,
    val swiftCodes: List<SwiftCodeResponse>
)

data class SwiftCodeResponse(
    val bankName: String,
    val countryISO2: String,
    val isHeadquarter: Boolean,
    val swiftCode: String,



)


data class BranchResponse(
    val address: String,
    val bankName: String,
    val countryISO2: String,
    val countryName: String,
    val isHeadquarter: Boolean,
    val swiftCode: String
)

data class SimpleBranchResponse(
    val address: String,
    val bankName: String,
    val countryISO2: String,
    val isHeadquarter: Boolean,
    val swiftCode: String
)