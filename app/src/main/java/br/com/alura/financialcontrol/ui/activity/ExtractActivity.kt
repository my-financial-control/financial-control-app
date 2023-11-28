package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import br.com.alura.financialcontrol.databinding.ExtractActivityBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.FinancialControl
import br.com.alura.financialcontrol.integration.types.CheckBalanceResponse
import br.com.alura.financialcontrol.integration.types.TransactionResponse
import br.com.alura.financialcontrol.ui.recyclerview.ExtractListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal

class ExtractActivity : AppCompatActivity() {

    private val transactionsLiveData = MutableLiveData<List<TransactionResponse>>()
    private val binding by lazy {
        ExtractActivityBinding.inflate(layoutInflater)
    }
    private val financialControlAPI = FinancialControl.getFinancialAPIInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        getBalance()
        configRecyclerViewExtract()
        observeTransactions()
    }

    private fun configRecyclerViewExtract() {
        val extractRecyclerViewAdapter =
            ExtractListAdapter(context = this, transactions = transactionsLiveData.value.orEmpty())
        val recyclerView = binding.extractListRecyclerView
        recyclerView.adapter = extractRecyclerViewAdapter
    }

    private fun getBalance() {
        val callback = financialControlAPI.checkBalance(null, null)
        callback.enqueue(object : Callback<CheckBalanceResponse> {
            override fun onFailure(call: Call<CheckBalanceResponse>, t: Throwable) {
                binding.availableBalanceTextView.text = BigDecimal.ZERO.toPtBr()
            }

            override fun onResponse(
                call: Call<CheckBalanceResponse>,
                response: Response<CheckBalanceResponse>
            ) {
                val body = response.body()
                binding.availableBalanceTextView.text = body?.balance?.toPtBr()
            }
        })
    }

    private fun getAllTransactions() {
        val callback = financialControlAPI.findAllTransactions(null, null)
        callback.enqueue(object : Callback<List<TransactionResponse>> {
            override fun onFailure(call: Call<List<TransactionResponse>>, t: Throwable) {}

            override fun onResponse(
                call: Call<List<TransactionResponse>>,
                response: Response<List<TransactionResponse>>
            ) {
                transactionsLiveData.value = response.body() ?: emptyList()
            }
        })
    }

    private fun observeTransactions() {
        transactionsLiveData.observe(this) {
            configRecyclerViewExtract()
        }
        getAllTransactions()
    }

}