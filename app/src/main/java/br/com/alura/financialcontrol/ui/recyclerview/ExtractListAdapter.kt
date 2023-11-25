package br.com.alura.financialcontrol.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.financialcontrol.databinding.ExtractListItemBinding
import br.com.alura.financialcontrol.extensions.defaultFormat
import br.com.alura.financialcontrol.extensions.toPtBr
import br.com.alura.financialcontrol.model.Transaction

class ExtractListAdapter(
    private val context: Context,
    transactions: List<Transaction>
) : RecyclerView.Adapter<ExtractListAdapter.ViewHolder>() {

    private val transactions = transactions.toMutableList()

    class ViewHolder(private val binding: ExtractListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.transactionTitleTextField
        private val value = binding.transactionValueTextField
        private val date = binding.transactionDateTextField

        fun bind(transaction: Transaction) {
            this.title.text = transaction.title
            this.value.text = transaction.value.toPtBr()
            this.date.text = transaction.date.defaultFormat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExtractListItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

}