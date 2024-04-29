package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.repositories.TransactionRepository
import br.com.alura.financialcontrol.integration.dtos.request.CreateTransactionRequestDTO
import br.com.alura.financialcontrol.integration.dtos.response.TransactionResponseDTO

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    fun findAllTransactions(
        month: Int? = null,
        year: Int? = null
    ): LiveData<Result<List<TransactionResponseDTO>?>> =
        repository.findAllTransactions(month, year)

    fun registerTransaction(request: CreateTransactionRequestDTO): LiveData<Result<TransactionResponseDTO?>> =
        repository.registerTransaction(request)
}