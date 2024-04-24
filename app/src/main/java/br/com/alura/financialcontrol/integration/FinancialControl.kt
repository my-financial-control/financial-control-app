package br.com.alura.financialcontrol.integration

import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.integration.network.NetworkUtils

class FinancialControl {

    companion object {
        fun getFinancialAPIInstance(): TransactionService {
            return NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
                .create(TransactionService::class.java)
        }
    }

}