package com.PatrykKusper.SWIFTcontroller.Services

import com.PatrykKusper.SWIFTcontroller.SwiftCode
import com.PatrykKusper.SWIFTcontroller.SwiftCodeParser
import com.PatrykKusper.SWIFTcontroller.SwiftRepository
import org.springframework.stereotype.Service
import java.io.InputStream
import java.io.InputStreamReader


@Service
class SwiftCodeImportService(
    private val swiftCodeRepository: SwiftRepository,
    private val parser: SwiftCodeParser
) {

    fun importFromCsv(inputStream: InputStream): List<SwiftCode> {
        val dtos = parser.parseCsv(inputStream)
        val swiftCodes = dtos.map { dto ->
            SwiftCode(
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
