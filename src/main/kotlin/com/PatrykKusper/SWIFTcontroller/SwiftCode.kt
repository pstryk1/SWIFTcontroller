package com.PatrykKusper.SWIFTcontroller

import jakarta.persistence.*


@Entity
@Table(name = "swift_codes")
data class SwiftCode(
    @Id
    val swiftCode: String,

    val bankName: String,
    val address: String,

    @Column(nullable = false)
    val iso: String,

    @Column(nullable = false)
    val countryName: String,

    val isHeadquarter: Boolean,

    // Relacja: tylko dla headquarter, lista oddziałów. Może być null dla branch.
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "headquarter_code", referencedColumnName = "swiftCode")
    val branches: List<SwiftCode>? = null
)
