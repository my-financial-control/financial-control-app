package br.com.alura.financialcontrol.integration.types

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class TransactionResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val value: BigDecimal,
    val type: String,
    val currentMonth: Int,
    val date: LocalDate,
    val time: LocalTime
)
