package br.com.alura.financialcontrol.model

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(
    val title: String,
    val value: BigDecimal,
    val description: String,
    val date: LocalDate,
    val type: String,
)
