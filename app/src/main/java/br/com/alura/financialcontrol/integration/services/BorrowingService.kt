package br.com.alura.financialcontrol.integration.services

import br.com.alura.financialcontrol.integration.dtos.request.PayParcelBorrowingRequestDTO
import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BorrowingService {
    @GET("api/v1/borrowings")
    suspend fun findAll(): Response<List<BorrowingResponseDTO>?>

    @POST("api/v1/borrowings/{id}/parcels")
    suspend fun payParcel(@Path(value = "id") id: String, @Body request: PayParcelBorrowingRequestDTO): Response<Void>
}