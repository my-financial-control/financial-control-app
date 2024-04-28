package br.com.alura.financialcontrol.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.databinding.MainActivityBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.FinancialControl
import br.com.alura.financialcontrol.integration.Result
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

}
