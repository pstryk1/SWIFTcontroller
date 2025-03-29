package com.PatrykKusper.SWIFTcontroller

import com.PatrykKusper.SWIFTcontroller.Services.SwiftCodeImportService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.InputStreamReader

@Component
class DataInitializer(
    private val swiftCodeImportService: SwiftCodeImportService
) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val inputStream = this::class.java.getResourceAsStream("/swift_codes.csv")
            ?: throw IllegalArgumentException("Plik CSV nie został znaleziony.")
        //val reader = InputStreamReader(inputStream)

        // Import danych
        swiftCodeImportService.importFromCsv(inputStream)
    }
}
