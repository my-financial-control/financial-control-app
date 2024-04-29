package br.com.alura.financialcontrol.integration.types

import java.math.BigDecimal
import java.time.LocalDate

data class CreateTransactionRequest(
    val title: String,
    val description: String,
    val value: BigDecimal,
    val type: String,
    val currentMonth: Int = LocalDate.now().month.value,
    val date: String = LocalDate.now().toString()
)
