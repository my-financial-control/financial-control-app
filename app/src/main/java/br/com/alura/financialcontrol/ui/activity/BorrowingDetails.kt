package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financialcontrol.R
import br.com.alura.financialcontrol.databinding.ActivityBorrowingDetailsBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO
import br.com.alura.financialcontrol.ui.recyclerview.BorrowingParcelsListAdapter
import java.math.BigDecimal

class BorrowingDetails : AppCompatActivity() {
    private val binding by lazy {
        ActivityBorrowingDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val borrowing = intent.getParcelableExtra<BorrowingResponseDTO>("borrowing")!!
        configTextViews(borrowing)
        configRecyclerViewBorrowingParcels(borrowing)
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
        val parcelsRecyclerViewAdapter =
            BorrowingParcelsListAdapter(this, borrowing.parcels)
        val recyclerView = binding.recyclerViewParcelsBorrowing
        recyclerView.adapter = parcelsRecyclerViewAdapter
    }
}