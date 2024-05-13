package br.com.alura.financialcontrol.integration.repositories

import androidx.lifecycle.liveData
import br.com.alura.financialcontrol.integration.dtos.request.PayParcelBorrowingRequestDTO
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.services.BorrowingService
import java.net.ConnectException

class BorrowingRepository(private val service: BorrowingService) {
    fun findAll() = liveData {
        try {
            val response = service.findAll()
            if (response.isSuccessful) {
                emit(Result.Success(response.body()))
            } else {
                emit(Result.Error(Exception("Failed to fetch borrowings")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(Exception("Failed to communicate with API")))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun payParcel(id: String, request: PayParcelBorrowingRequestDTO) =
        liveData {
            try {
                val response = service.payParcel(id, request)
                if (response.isSuccessful) {
                    emit(Result.Success(response.body()))
                } else {
                    emit(Result.Error(Exception("Failed to pay parcel")))
                }
            } catch (e: ConnectException) {
                emit(Result.Error(Exception("Failed to communicate with API")))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}