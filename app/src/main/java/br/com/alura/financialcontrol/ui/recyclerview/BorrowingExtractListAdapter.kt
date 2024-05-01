package br.com.alura.financialcontrol.ui.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.financialcontrol.R
import br.com.alura.financialcontrol.databinding.BorrowingExtractListItemBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.dtos.response.BorrowingResponseDTO

class BorrowingExtractListAdapter(
    private val context: Context,
    borrowings: List<BorrowingResponseDTO>
) : RecyclerView.Adapter<BorrowingExtractListAdapter.ViewHolder>() {
    private val borrowings = borrowings.toMutableList()

    class ViewHolder(private val binding: BorrowingExtractListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val borrower = binding.borrowerTextView
        private val value = binding.borrowingValueTextView
        private val date = binding.borrowingDateTextView
        private val ellipseIsPaidBorrowing = binding.ellipse

        @SuppressLint("SetTextI18n")
        fun bind(borrowing: BorrowingResponseDTO) {
            date.text = borrowing.date
            borrower.text = borrowing.borrower
            value.text = "- ${borrowing.value.toPtBr()}"
            if (!borrowing.paid) {
                ellipseIsPaidBorrowing.setImageResource(R.drawable.circle_shape_red)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BorrowingExtractListItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return borrowings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val borrowing = borrowings[position]
        holder.bind(borrowing)
    }

}