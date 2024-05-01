package br.com.alura.financialcontrol.integration.dtos.response

import java.math.BigDecimal

data class BorrowingResponseDTO(
    val id: String,
    val borrower: String,
    val value: BigDecimal,
    val paid: Boolean,
    val date: String,
    val parcels: List<ParcelBorrowingResponseDTO>
)
