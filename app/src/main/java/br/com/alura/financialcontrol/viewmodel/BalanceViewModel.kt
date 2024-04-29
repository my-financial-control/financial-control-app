package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.repositories.BalanceRepository
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.dtos.response.CheckBalanceResponseDTO

class BalanceViewModel(private val repository: BalanceRepository) : ViewModel() {
    fun checkBalance(month: Int? = null, year: Int? = null): LiveData<Result<CheckBalanceResponseDTO?>> =
        repository.checkBalance(month, year)
}