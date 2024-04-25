package br.com.alura.financialcontrol.integration

import br.com.alura.financialcontrol.integration.types.CheckBalanceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {
    @GET("api/v1/check-balance")
    suspend fun checkBalance(
        @Query("month") month: Int?,
        @Query("year") year: Int?
    ): Response<CheckBalanceResponse>
}