package com.PatrykKusper.SWIFTcontroller.Services

import com.PatrykKusper.SWIFTcontroller.model.SwiftCodeDataClass
import com.PatrykKusper.SWIFTcontroller.repository.SwiftRepository
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class SwiftCodeImportService(
    private val swiftCodeRepository: SwiftRepository,
    private val parser: SwiftCodeParser
) {

    fun importFromCsv(inputStream: InputStream): List<SwiftCodeDataClass> {
        val dtos = parser.parseCsv(inputStream)
        val swiftCodes = dtos.map { dto ->
            SwiftCodeDataClass(
                swiftCode = dto.swiftCode,
                bankName = dto.bankName,
                address = dto.address,
                iso = dto.countryISO2,
                countryName = dto.countryName,
                isHeadquarter = dto.isHeadquarter
            )
        }

        return swiftCodeRepository.saveAll(swiftCodes)
    }
}
