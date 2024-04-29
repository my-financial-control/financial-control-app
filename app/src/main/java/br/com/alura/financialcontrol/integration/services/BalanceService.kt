package br.com.alura.financialcontrol.integration.services

import br.com.alura.financialcontrol.integration.dtos.response.CheckBalanceResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {
    @GET("api/v1/check-balance")
    suspend fun checkBalance(
        @Query("month") month: Int?,
        @Query("year") year: Int?
    ): Response<CheckBalanceResponseDTO>
}