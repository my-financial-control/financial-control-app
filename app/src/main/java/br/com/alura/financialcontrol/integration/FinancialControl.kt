package br.com.alura.financialcontrol.integration

import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.utils.NetworkUtils

class FinancialControl {

    companion object {
        fun getFinancialAPIInstance(): FinancialControlAPI {
            return NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
                .create(FinancialControlAPI::class.java)
        }
    }

}