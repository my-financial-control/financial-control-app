package br.com.alura.financialcontrol.integration

import br.com.alura.financialcontrol.integration.types.CheckBalanceResponse
import br.com.alura.financialcontrol.integration.types.TransactionCreateRequest
import br.com.alura.financialcontrol.integration.types.TransactionResponse
import retrofit2.Call
import retrofit2.http.*

interface FinancialControlAPI {

    @GET("api/v1/transactions")
    fun findAllTransactions(
        @Query("month") month: Int?,
        @Query("year") year: Int?
    ): Call<List<TransactionResponse>>

    @POST("api/v1/transactions")
    fun registerTransaction(@Body request: TransactionCreateRequest): Call<TransactionResponse>

    @GET("api/v1/check-balance")
    fun checkBalance(
        @Query("month") month: Int?,
        @Query("year") year: Int?
    ): Call<CheckBalanceResponse>

}