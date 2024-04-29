package br.com.alura.financialcontrol.integration.repositories

import androidx.lifecycle.liveData
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.dtos.request.CreateTransactionRequestDTO
import br.com.alura.financialcontrol.integration.services.TransactionService
import java.net.ConnectException


class TransactionRepository(private val service: TransactionService) {
    fun findAllTransactions(month: Int?, year: Int?) = liveData {
        try {
            val response = service.findAllTransactions(month, year)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()))
            } else {
                emit(Result.Error(Exception("Failed to fetch transactions")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(Exception("Failed to communicate with API")))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun registerTransaction(request: CreateTransactionRequestDTO) = liveData {
        try {
            val response = service.registerTransaction(request)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()))
            } else {
                emit(Result.Error(Exception("Failed to register the transaction")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(Exception("Failed to communicate with API")))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}