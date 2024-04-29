package br.com.alura.financialcontrol.integration.services

import br.com.alura.financialcontrol.integration.dtos.request.CreateTransactionRequestDTO
import br.com.alura.financialcontrol.integration.dtos.response.TransactionResponseDTO
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
    ): Response<List<TransactionResponseDTO>>

    @POST("api/v1/transactions")
    suspend fun registerTransaction(@Body request: CreateTransactionRequestDTO): Response<TransactionResponseDTO>
}