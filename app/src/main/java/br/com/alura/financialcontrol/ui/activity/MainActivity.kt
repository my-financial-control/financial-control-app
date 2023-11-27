package br.com.alura.financialcontrol.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financialcontrol.BuildConfig
import br.com.alura.financialcontrol.databinding.MainActivityBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.FinancialControlAPI
import br.com.alura.financialcontrol.integration.types.CheckBalanceResponse
import br.com.alura.financialcontrol.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }
    private val financialControlAPI =
        NetworkUtils.getRetrofitInstance(BuildConfig.FINANCIAL_CONTROL_API_HOST)
            .create(FinancialControlAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        configNavigation()
        getBalance()
    }

    private fun configNavigation() {
        val newTransactionCardView = binding.newTransactionMaterialCardView
        newTransactionCardView.setOnClickListener {
            val intent = Intent(this, NewTransactionActivity::class.java)
            startActivity(intent)
        }

        val seeExtractTextView = binding.seeExtractTextView
        seeExtractTextView.setOnClickListener {
            val intent = Intent(this, ExtractActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getBalance() {
        val callback = financialControlAPI.checkBalance(null, null)
        callback.enqueue(object : Callback<CheckBalanceResponse> {
            override fun onFailure(call: Call<CheckBalanceResponse>, t: Throwable) {
                binding.balanceValueTextView.text = BigDecimal.ZERO.toPtBr()
            }

            override fun onResponse(
                call: Call<CheckBalanceResponse>,
                response: Response<CheckBalanceResponse>
            ) {
                val body = response.body()
                binding.balanceValueTextView.text = body?.balance?.toPtBr()
            }
        })
    }

}
