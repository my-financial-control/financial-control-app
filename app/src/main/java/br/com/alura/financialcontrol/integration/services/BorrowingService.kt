package br.com.alura.financialcontrol.integration.services

import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface BorrowingService {
    @GET("api/v1/borrowings")
    suspend fun findAll(): Response<List<BorrowingResponseDTO>?>
}