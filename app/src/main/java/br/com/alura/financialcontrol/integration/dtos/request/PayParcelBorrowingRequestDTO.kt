package br.com.alura.financialcontrol.integration.dtos.request

import java.math.BigDecimal

data class PayParcelBorrowingRequestDTO(val value: BigDecimal, val date: String)
