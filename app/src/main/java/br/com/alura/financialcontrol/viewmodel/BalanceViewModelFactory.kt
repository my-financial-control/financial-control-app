package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.integration.BalanceRepository
import br.com.alura.financialcontrol.integration.BalanceService
import br.com.alura.financialcontrol.integration.network.NetworkUtils

@Suppress("UNCHECKED_CAST")
class BalanceViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalanceViewModel::class.java)) {
            val repository = BalanceRepository(
                NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
                    .create(BalanceService::class.java)
            )
            return BalanceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}