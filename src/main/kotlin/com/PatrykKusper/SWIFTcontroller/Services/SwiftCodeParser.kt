package com.PatrykKusper.SWIFTcontroller.Services


import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import org.springframework.stereotype.Component
import java.io.InputStream



data class SwiftCodeDTO(
    val countryISO2: String,
    val swiftCode: String,
    val codeType: String,
    val bankName: String,
    val address: String,
    val townName: String,
    val countryName: String,
    val timeZone: String,
    val isHeadquarter: Boolean = swiftCode.endsWith("XXX")
)


@Component
class SwiftCodeParser {

    fun parseCsv(inputStream: InputStream): List<SwiftCodeDTO> {
        val csvReader = CsvReader()
        val records = csvReader.readAllWithHeader(inputStream)
        return records.map { row ->
            SwiftCodeDTO(
                countryISO2 = row["COUNTRY ISO2 CODE"]?.uppercase() ?: "",
                swiftCode = row["SWIFT CODE"] ?: "",
                codeType = row["CODE TYPE"] ?: "",
                bankName = row["NAME"] ?: "",
                address = row["ADDRESS"] ?: "",
                townName = row["TOWN NAME"] ?: "",
                countryName = row["COUNTRY NAME"]?.uppercase() ?: "",
                timeZone = row["TIME ZONE"] ?: "",
                isHeadquarter = row["SWIFT CODE"]?.endsWith("XXX") ?: false
            )
        }
    }
}