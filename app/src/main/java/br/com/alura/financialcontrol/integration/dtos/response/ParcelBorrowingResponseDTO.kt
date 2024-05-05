package br.com.alura.financialcontrol.integration.dtos.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class ParcelBorrowingResponseDTO(
    val value: BigDecimal,
    val date: String
) : Parcelable
