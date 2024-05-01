package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.financialcontrol.integration.dtos.response.CheckBalanceResponseDTO
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.repositories.BalanceRepository

class BalanceViewModel(private val repository: BalanceRepository) : ViewModel() {
    fun checkBalance(
        month: Int? = null,
        year: Int? = null
    ): LiveData<Result<CheckBalanceResponseDTO?>> =
        repository.checkBalance(month, year)

    fun checkBalancePlusRemainingPayments(
        month: Int? = null,
        year: Int? = null
    ): LiveData<Result<CheckBalanceResponseDTO?>> =
        repository.checkBalancePlusRemainingPayments(month, year)
}