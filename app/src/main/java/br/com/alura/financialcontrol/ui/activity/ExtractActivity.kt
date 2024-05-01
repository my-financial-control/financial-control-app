package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.databinding.ExtractActivityBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.ui.recyclerview.ExtractListAdapter
import br.com.alura.financialcontrol.viewmodel.BalanceViewModel
import br.com.alura.financialcontrol.viewmodel.BalanceViewModelFactory
import br.com.alura.financialcontrol.viewmodel.TransactionViewModel
import br.com.alura.financialcontrol.viewmodel.TransactionViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal

class ExtractActivity : AppCompatActivity() {

    private val binding by lazy {
        ExtractActivityBinding.inflate(layoutInflater)
    }
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var balanceViewModel: BalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        transactionViewModel =
            ViewModelProvider(this, TransactionViewModelFactory())[TransactionViewModel::class.java]
        balanceViewModel =
            ViewModelProvider(this, BalanceViewModelFactory())[BalanceViewModel::class.java]
        getBalance()
        getBalancePlusRemainingPayments()
        configRecyclerViewExtract()
    }

    private fun getBalance() {
        balanceViewModel.checkBalance().observe(this) {
            val balance = it.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.balance.toPtBr()
                    }

                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            result.exception.message.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        BigDecimal.ZERO.toPtBr()
                    }
                }
            }
            binding.availableBalanceTextView.text = balance
        }
    }

    private fun getBalancePlusRemainingPayments() {
        balanceViewModel.checkBalancePlusRemainingPayments().observe(this) {
            val balance = it.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.balance.toPtBr()
                    }

                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            result.exception.message.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        BigDecimal.ZERO.toPtBr()
                    }
                }
            }
            binding.availableBalancePlusToReceiveValueTextView.text = balance
        }
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

}