package br.com.alura.financialcontrol.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financialcontrol.databinding.ExtractActivityBinding
import br.com.alura.financialcontrol.model.Transaction
import br.com.alura.financialcontrol.ui.recyclerview.ExtractListAdapter
import java.math.BigDecimal
import java.time.LocalDate

class ExtractActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val transactions = mutableListOf<Transaction>(
        Transaction("Salário", BigDecimal("1580.78"), "", LocalDate.now(), "credit"),
        Transaction("Luz", BigDecimal("147.41"), "", LocalDate.now(), "expense"),
        Transaction("Água", BigDecimal("110.83"), "", LocalDate.now(), "expense"),
        Transaction("Internet", BigDecimal("120.0"), "", LocalDate.now(), "expense"),
        Transaction("Mercado", BigDecimal("900.0"), "", LocalDate.now(), "expense"),
        Transaction("Vale", BigDecimal("1200.77"), "", LocalDate.now(), "credit"),
        Transaction("Salário", BigDecimal("1580.78"), "", LocalDate.now(), "credit"),
        Transaction("Luz", BigDecimal("147.41"), "", LocalDate.now(), "expense"),
        Transaction("Água", BigDecimal("110.83"), "", LocalDate.now(), "expense"),
        Transaction("Internet", BigDecimal("120.0"), "", LocalDate.now(), "expense"),
        Transaction("Mercado", BigDecimal("900.0"), "", LocalDate.now(), "expense"),
        Transaction("Vale", BigDecimal("1200.77"), "", LocalDate.now(), "credit"),
        Transaction("Salário", BigDecimal("1580.78"), "", LocalDate.now(), "credit"),
        Transaction("Luz", BigDecimal("147.41"), "", LocalDate.now(), "expense"),
        Transaction("Água", BigDecimal("110.83"), "", LocalDate.now(), "expense"),
        Transaction("Internet", BigDecimal("120.0"), "", LocalDate.now(), "expense"),
        Transaction("Mercado", BigDecimal("900.0"), "", LocalDate.now(), "expense"),
        Transaction("Vale", BigDecimal("1200.77"), "", LocalDate.now(), "credit"),
        Transaction("Salário", BigDecimal("1580.78"), "", LocalDate.now(), "credit"),
        Transaction("Luz", BigDecimal("147.41"), "", LocalDate.now(), "expense"),
        Transaction("Água", BigDecimal("110.83"), "", LocalDate.now(), "expense"),
        Transaction("Internet", BigDecimal("120.0"), "", LocalDate.now(), "expense"),
        Transaction("Mercado", BigDecimal("900.0"), "", LocalDate.now(), "expense"),
        Transaction("Vale", BigDecimal("1200.77"), "", LocalDate.now(), "credit"),
        Transaction("Salário", BigDecimal("1580.78"), "", LocalDate.now(), "credit"),
        Transaction("Luz", BigDecimal("147.41"), "", LocalDate.now(), "expense"),
        Transaction("Água", BigDecimal("110.83"), "", LocalDate.now(), "expense"),
        Transaction("Internet", BigDecimal("120.0"), "", LocalDate.now(), "expense"),
        Transaction("Mercado", BigDecimal("900.0"), "", LocalDate.now(), "expense"),
        Transaction("Vale", BigDecimal("1200.77"), "", LocalDate.now(), "credit"),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private val extractRecyclerViewAdapter =
        ExtractListAdapter(context = this, transactions = transactions)
    private val binding by lazy {
        ExtractActivityBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        configRecyclerViewExtract()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configRecyclerViewExtract() {
        val recyclerView = this.binding.extractListRecyclerView
        recyclerView.adapter = this.extractRecyclerViewAdapter
    }

}