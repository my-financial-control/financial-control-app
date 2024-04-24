package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.integration.TransactionRepository
import br.com.alura.financialcontrol.integration.TransactionService
import br.com.alura.financialcontrol.integration.network.NetworkUtils

@Suppress("UNCHECKED_CAST")
class TransactionViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            val repository = TransactionRepository(
                NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
                    .create(TransactionService::class.java)
            )
            return TransactionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
