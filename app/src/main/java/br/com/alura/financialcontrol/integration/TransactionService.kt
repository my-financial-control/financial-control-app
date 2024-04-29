package br.com.alura.financialcontrol.integration

import br.com.alura.financialcontrol.integration.types.CreateTransactionRequest
import br.com.alura.financialcontrol.integration.types.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransactionService {
    @GET("api/v1/transactions")
    suspend fun findAllTransactions(
        @Query("month") month: Int?,
        @Query("year") year: Int?
    ): Response<List<TransactionResponse>>

    @POST("api/v1/transactions")
    suspend fun registerTransaction(@Body request: CreateTransactionRequest): Response<TransactionResponse>
}