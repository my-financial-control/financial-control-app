package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.Result
import br.com.alura.financialcontrol.integration.TransactionRepository
import br.com.alura.financialcontrol.integration.types.CreateTransactionRequest
import br.com.alura.financialcontrol.integration.types.TransactionResponse

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    fun findAllTransactions(
        month: Int? = null,
        year: Int? = null
    ): LiveData<Result<List<TransactionResponse>?>> =
        repository.findAllTransactions(month, year)

    fun registerTransaction(request: CreateTransactionRequest): LiveData<Result<TransactionResponse?>> =
        repository.registerTransaction(request)
}