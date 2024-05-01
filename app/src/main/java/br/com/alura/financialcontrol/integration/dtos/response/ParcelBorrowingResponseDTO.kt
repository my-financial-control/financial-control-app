package br.com.alura.financialcontrol.integration.dtos.response

import java.math.BigDecimal

data class ParcelBorrowingResponseDTO(
    val value: BigDecimal,
    val date: String
)
