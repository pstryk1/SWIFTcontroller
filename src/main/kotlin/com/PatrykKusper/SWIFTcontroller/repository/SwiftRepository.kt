package com.PatrykKusper.SWIFTcontroller.repository

import com.PatrykKusper.SWIFTcontroller.model.SwiftCodeDataClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SwiftRepository:JpaRepository<SwiftCodeDataClass, String> {

    fun findByiso(countryISO2:String):List<SwiftCodeDataClass>
    fun findBySwiftCode(swiftCode: String): SwiftCodeDataClass?
}