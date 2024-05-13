package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.alura.financialcontrol.R
import br.com.alura.financialcontrol.databinding.ActivityBorrowingDetailsBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO
import br.com.alura.financialcontrol.ui.dialog.RegisterParcelDialog
import br.com.alura.financialcontrol.ui.recyclerview.BorrowingParcelsListAdapter
import br.com.alura.financialcontrol.viewmodel.BorrowingViewModel
import br.com.alura.financialcontrol.viewmodel.BorrowingViewModelFactory
import java.math.BigDecimal

class BorrowingDetails : AppCompatActivity() {
    private val binding by lazy {
        ActivityBorrowingDetailsBinding.inflate(layoutInflater)
    }
    private lateinit var borrowingViewModel: BorrowingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        borrowingViewModel =
            ViewModelProvider(this, BorrowingViewModelFactory())[BorrowingViewModel::class.java]
        val borrowing = intent.getParcelableExtra<BorrowingResponseDTO>("borrowing")!!
        configTextViews(borrowing)
        configRecyclerViewBorrowingParcels(borrowing)
        configFabRegisterParcel(borrowing)
    }

    private fun configTextViews(borrowing: BorrowingResponseDTO) {
        binding.textViewBorrowerName.text = borrowing.borrower
        binding.textViewBorrowingValue.text = borrowing.value.toPtBr()
        val valuePaid = borrowing.parcels.fold(BigDecimal.ZERO) { acc, parcel ->
            acc.add(parcel.value)
        }
        binding.textViewBorrowingAmountPaid.text = valuePaid.toPtBr()
        binding.textViewBorrowingAmountToReceive.text = borrowing.value.subtract(valuePaid).toPtBr()
        if (borrowing.paid) {
            binding.txtViewBorrowingStatus.text = getString(R.string.pago_status)
            binding.txtViewBorrowingStatus.setBackgroundResource(R.drawable.retangle_shape_green)
        } else {
            binding.txtViewBorrowingStatus.setBackgroundResource(R.drawable.retangle_shape_red)
            binding.txtViewBorrowingStatus.text = getString(R.string.pendente_status)
        }
    }

    private fun configRecyclerViewBorrowingParcels(borrowing: BorrowingResponseDTO) {
        val parcelsRecyclerViewAdapter = BorrowingParcelsListAdapter(this, borrowing.parcels)
        val recyclerView = binding.recyclerViewParcelsBorrowing
        recyclerView.adapter = parcelsRecyclerViewAdapter
    }

    private fun configFabRegisterParcel(borrowing: BorrowingResponseDTO) {
        if (borrowing.paid) {
            binding.registerParcelFab.hide()
        } else {
            binding.registerParcelFab.setOnClickListener {
                RegisterParcelDialog(this, borrowing.id, this, borrowingViewModel).show()
            }
        }

    }
}