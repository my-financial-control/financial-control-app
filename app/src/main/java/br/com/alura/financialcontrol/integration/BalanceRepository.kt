package br.com.alura.financialcontrol.integration

import androidx.lifecycle.liveData
import java.net.ConnectException

class BalanceRepository(private val service: BalanceService) {
    fun checkBalance(month: Int?, year: Int?) = liveData {
        try {
            val response = service.checkBalance(month, year)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()))
            } else {
                emit(Result.Error(Exception("Failed to fetch balance")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(Exception("Failed to communicate with API")))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}