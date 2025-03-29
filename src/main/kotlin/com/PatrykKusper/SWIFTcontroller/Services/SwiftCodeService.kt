package com.PatrykKusper.SWIFTcontroller.Services

import com.PatrykKusper.SWIFTcontroller.SwiftCode
import com.PatrykKusper.SWIFTcontroller.SwiftRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class SwiftCodeService(private val repository: SwiftRepository) {

    fun getSwiftCode(swiftCode: String): SwiftCode {
        return repository.findBySwiftCode(swiftCode)
            ?: throw NotFoundException("SWIFT code: $swiftCode can not be found.")
    }

    fun getSwiftCodesByCountry(countryISO2: String): List<SwiftCode> {
        return repository.findByiso(countryISO2.uppercase())
    }

    fun addSwiftCode(newCode: SwiftCode): SwiftCode {
        if (repository.existsById(newCode.swiftCode)) {
            throw BadRequestException("SWIFT code ${newCode.swiftCode} already exists.")
        }
        return repository.save(newCode)
    }

    fun deleteSwiftCode(swiftCode: String) {
        if (!repository.existsById(swiftCode)) {
            throw NotFoundException("SWIFT code: $swiftCode can not be found.")
        }
        repository.deleteById(swiftCode)
    }
    fun assignBranchesToHeadquarters(): List<HeadquarterWithBranches> {
        val allCodes = repository.findAll()

        val headquarters = allCodes.filter { it.isHeadquarter }
        val branches = allCodes.filter { !it.isHeadquarter }

        return headquarters.map { hq ->
            val hqPrefix = hq.swiftCode.take(8)
            val hqBranches = branches.filter { it.swiftCode.take(8) == hqPrefix }
            HeadquarterWithBranches(headquarter = hq, branches = hqBranches)
        }
    }

    fun getBranchesForHeadquarter(headquarter: SwiftCode): List<SwiftCode> {
        val hqPrefix = headquarter.swiftCode.take(8)
        return repository.findAll().filter {
            !it.isHeadquarter && it.swiftCode.startsWith(hqPrefix)
        }
    }
}

data class HeadquarterWithBranches(
    val headquarter: SwiftCode,
    val branches: List<SwiftCode>
)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(message: String) : RuntimeException(message)
