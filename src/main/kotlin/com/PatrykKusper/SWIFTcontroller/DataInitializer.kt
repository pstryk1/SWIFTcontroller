package com.PatrykKusper.SWIFTcontroller

import com.PatrykKusper.SWIFTcontroller.Services.SwiftCodeImportService
import com.PatrykKusper.SWIFTcontroller.repository.SwiftRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.InputStreamReader

@Component
class DataInitializer(
    private val swiftCodeImportService: SwiftCodeImportService,
    private val swiftRepository: SwiftRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (swiftRepository.count() == 0L) {
            val inputStream = this::class.java.getResourceAsStream("/swift_codes.csv")
                ?: throw IllegalArgumentException("CSV file not found")
            swiftCodeImportService.importFromCsv(inputStream)
            println("Data load completed")
        } else {
            println("Data load skipped, database is not empty")
        }
    }
}
