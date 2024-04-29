package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.R
import br.com.alura.financialcontrol.databinding.NewTransactionActivityBinding
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.integration.dtos.request.CreateTransactionRequestDTO
import br.com.alura.financialcontrol.ui.components.buildDatePicker
import br.com.alura.financialcontrol.utils.DEFAULT_DATE_FORMAT
import br.com.alura.financialcontrol.utils.monthFromPtBr
import br.com.alura.financialcontrol.viewmodel.TransactionViewModel
import br.com.alura.financialcontrol.viewmodel.TransactionViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.math.BigDecimal
import java.time.LocalDate

class NewTransactionActivity : AppCompatActivity() {

    private val binding by lazy {
        NewTransactionActivityBinding.inflate(layoutInflater)
    }
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var datePickerTransactionDate: MaterialDatePicker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        transactionViewModel =
            ViewModelProvider(this, TransactionViewModelFactory())[TransactionViewModel::class.java]
        configEditTextEventDate()
        configRegisterTransactionButton()
    }

    private fun configEditTextEventDate() {
        val editTextDate = binding.transactionDateInput
        datePickerTransactionDate =
            buildDatePicker(editTextDate, supportFragmentManager, "Selecione a data")
    }

    private fun configRegisterTransactionButton() {
        var selectedTransactionType = ""
        binding.transactionTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.creditRadioButton -> selectedTransactionType = "CREDIT"
                R.id.expenseRadioButton -> selectedTransactionType = "EXPENSE"
            }
        }

        binding.saveButton.setOnClickListener {
            val request = CreateTransactionRequestDTO(
                binding.transactionTitleInput.text.toString(),
                binding.transactionDescriptionInput.text.toString(),
                BigDecimal(binding.transactionValueInput.text.toString()),
                selectedTransactionType,
                monthFromPtBr(binding.transactionCurrentMonthInput.text.toString()).value,
                LocalDate.parse(binding.transactionDateInput.text.toString(), DEFAULT_DATE_FORMAT)
                    .toString()
            )
            transactionViewModel.registerTransaction(request).observe(this) {
                it.let { result ->
                    when (result) {
                        is Result.Success -> {
                            Toast.makeText(
                                this,
                                "Transação registrada com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is Result.Error -> {
                            Toast.makeText(
                                this,
                                result.exception.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}