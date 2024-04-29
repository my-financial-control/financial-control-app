package br.com.alura.financialcontrol.integration.types

import java.math.BigDecimal

data class TransactionResponse(
    val id: String,
    val title: String,
    val description: String,
    val value: BigDecimal,
    val type: String,
    val currentMonth: String,
    val date: String,
    val time: String
)
