package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.databinding.ActivityBorrowingExtractBinding
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.ui.recyclerview.BorrowingExtractListAdapter
import br.com.alura.financialcontrol.viewmodel.BorrowingViewModel
import br.com.alura.financialcontrol.viewmodel.BorrowingViewModelFactory
import com.google.android.material.snackbar.Snackbar

class BorrowingExtract : AppCompatActivity() {
    private val binding by lazy {
        ActivityBorrowingExtractBinding.inflate(layoutInflater)
    }
    private lateinit var borrowingViewModel: BorrowingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        borrowingViewModel =
            ViewModelProvider(this, BorrowingViewModelFactory())[BorrowingViewModel::class.java]
        configRecyclerViewBorrowingsExtract()
    }

    private fun configRecyclerViewBorrowingsExtract() {
        borrowingViewModel.findAll().observe(this) {
            val borrowings = it?.let { result ->
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
                BorrowingExtractListAdapter(this, borrowings ?: emptyList())
            val recyclerView = binding.extractBorrowingsListRecyclerView
            recyclerView.adapter = extractRecyclerViewAdapter
        }
    }
}