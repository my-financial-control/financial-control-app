package br.com.alura.financialcontrol.integration.dtos.request

import java.math.BigDecimal
import java.time.LocalDate

data class CreateTransactionRequestDTO(
    val title: String,
    val description: String,
    val value: BigDecimal,
    val type: String,
    val currentMonth: Int = LocalDate.now().month.value,
    val date: String = LocalDate.now().toString()
)
