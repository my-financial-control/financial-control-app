package br.com.alura.financialcontrol.integration

import androidx.lifecycle.liveData
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
}