package br.com.alura.financialcontrol.integration.dtos.response

import java.math.BigDecimal

data class CheckBalanceResponseDTO(
    val balance: BigDecimal
)
