package br.com.alura.financialcontrol.integration.dtos.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class BorrowingResponseDTO(
    val id: String,
    val borrower: String,
    val value: BigDecimal,
    val paid: Boolean,
    val date: String,
    val parcels: List<ParcelBorrowingResponseDTO>
) : Parcelable
