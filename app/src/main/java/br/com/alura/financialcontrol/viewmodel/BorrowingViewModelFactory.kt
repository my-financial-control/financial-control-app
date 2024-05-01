package br.com.alura.financialcontrol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.integration.network.NetworkUtils
import br.com.alura.financialcontrol.integration.repositories.BorrowingRepository
import br.com.alura.financialcontrol.integration.services.BorrowingService

@Suppress("UNCHECKED_CAST")
class BorrowingViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BorrowingViewModel::class.java)) {
            val repository = BorrowingRepository(
                NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
                    .create(BorrowingService::class.java)
            )
            return BorrowingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}