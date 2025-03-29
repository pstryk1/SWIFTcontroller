package com.PatrykKusper.SWIFTcontroller

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SwiftRepository:JpaRepository<SwiftCode, String> {

    fun findByiso(countryISO2:String):List<SwiftCode>
    fun findBySwiftCode(swiftCode: String):SwiftCode?
}