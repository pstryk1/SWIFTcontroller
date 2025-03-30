package com.PatrykKusper.SWIFTcontroller.model

import jakarta.persistence.*


@Entity
@Table(name = "swift_codes")
data class SwiftCodeDataClass(
    @Id
    val swiftCode: String,

    val bankName: String,
    val address: String,

    @Column(nullable = false)
    val iso: String,

    @Column(nullable = false)
    val countryName: String,

    val isHeadquarter: Boolean,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "headquarter_code", referencedColumnName = "swiftCode")
    val branches: List<SwiftCodeDataClass>? = null
)
