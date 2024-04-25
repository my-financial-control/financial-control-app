package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.BalanceRepository
import br.com.alura.financialcontrol.integration.Result
import br.com.alura.financialcontrol.integration.types.CheckBalanceResponse

class BalanceViewModel(private val repository: BalanceRepository) : ViewModel() {
    fun checkBalance(month: Int? = null, year: Int? = null): LiveData<Result<CheckBalanceResponse?>> =
        repository.checkBalance(month, year)
}