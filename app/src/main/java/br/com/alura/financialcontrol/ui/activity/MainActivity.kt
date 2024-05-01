package br.com.alura.financialcontrol.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.databinding.MainActivityBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.viewmodel.BalanceViewModel
import br.com.alura.financialcontrol.viewmodel.BalanceViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }
    private lateinit var balanceViewModel: BalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        balanceViewModel =
            ViewModelProvider(this, BalanceViewModelFactory())[BalanceViewModel::class.java]
        getBalance()
        getBalancePlusRemainingPayments()
        configNavigation()
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
            binding.balanceValueTextView.text = balance
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

    private fun configNavigation() {
        binding.newTransactionMaterialCardView.setOnClickListener {
            startActivity(Intent(this, NewTransactionActivity::class.java))
        }

        binding.seeExtractTextView.setOnClickListener {
            startActivity(Intent(this, ExtractActivity::class.java))
        }

        binding.borrowingsMaterialCardView.setOnClickListener {
            startActivity(Intent(this, BorrowingExtract::class.java))
        }
    }

}
