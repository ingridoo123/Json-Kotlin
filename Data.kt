package com.example

import kotlinx.serialization.Serializable

@Serializable
data class Obiekt(
    val Typ: String,
    val Masa: String,
    val Czystość: String,
    val Barwa: String,
    val Pochodzenie: String,
    val Właściciel: String
)

@Serializable
data class Kategoria(
    val Typ: String,
    val `Wartość za uncję (USD)`: Int,
    val Czystość: String
)