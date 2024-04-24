package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.databinding.ExtractActivityBinding
import br.com.alura.financialcontrol.integration.Result
import br.com.alura.financialcontrol.ui.recyclerview.ExtractListAdapter
import br.com.alura.financialcontrol.viewmodel.TransactionViewModel
import br.com.alura.financialcontrol.viewmodel.TransactionViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ExtractActivity : AppCompatActivity() {

    private val binding by lazy {
        ExtractActivityBinding.inflate(layoutInflater)
    }
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        transactionViewModel =
            ViewModelProvider(this, TransactionViewModelFactory())[TransactionViewModel::class.java]
//        getBalance()
        configRecyclerViewExtract()
    }

    private fun configRecyclerViewExtract() {
        transactionViewModel.findAllTransactions().observe(this) {
            val transactions = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data
                    }

                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            result.exception.message.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        emptyList()
                    }
                }
            }
            val extractRecyclerViewAdapter =
                ExtractListAdapter(this, transactions ?: emptyList())
            val recyclerView = binding.extractListRecyclerView
            recyclerView.adapter = extractRecyclerViewAdapter
        }
    }

//    private fun getBalance() {
//        val callback = financialControlAPI.checkBalance(null, null)
//        callback.enqueue(object : Callback<CheckBalanceResponse> {
//            override fun onFailure(call: Call<CheckBalanceResponse>, t: Throwable) {
//                binding.availableBalanceTextView.text = BigDecimal.ZERO.toPtBr()
//            }
//
//            override fun onResponse(
//                call: Call<CheckBalanceResponse>,
//                response: Response<CheckBalanceResponse>
//            ) {
//                val body = response.body()
//                binding.availableBalanceTextView.text = body?.balance?.toPtBr()
//            }
//        })
//    }

}