package br.com.alura.financialcontrol.ui.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.financialcontrol.databinding.BorrowingParcelsListItemBinding
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.integration.dtos.response.ParcelBorrowingResponseDTO

class BorrowingParcelsListAdapter(
    private val context: Context,
    parcels: List<ParcelBorrowingResponseDTO>
) : RecyclerView.Adapter<BorrowingParcelsListAdapter.ViewHolder>() {
    private val parcels = parcels.toMutableList()

    class ViewHolder(private val binding: BorrowingParcelsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val date = binding.parcelPaymentDateTextView
        private val value = binding.parcelValueTextView

        @SuppressLint("SetTextI18n")
        fun bind(parcel: ParcelBorrowingResponseDTO) {
            date.text = parcel.date
            value.text = parcel.value.toPtBr()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BorrowingParcelsListItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parcels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parcel = parcels[position]
        holder.bind(parcel)
    }
}