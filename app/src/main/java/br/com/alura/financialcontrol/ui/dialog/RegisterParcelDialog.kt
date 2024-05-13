package br.com.alura.financialcontrol.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import br.com.alura.financialcontrol.R
import br.com.alura.financialcontrol.integration.dtos.request.PayParcelBorrowingRequestDTO
import br.com.alura.financialcontrol.integration.network.Result
import br.com.alura.financialcontrol.utils.DEFAULT_DATE_FORMAT
import br.com.alura.financialcontrol.viewmodel.BorrowingViewModel
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.time.LocalDate

class RegisterParcelDialog(
    private val context: Context,
    private val borrowingId: String,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BorrowingViewModel
) {

    private val inflater = LayoutInflater.from(context)
    private val view = inflater.inflate(R.layout.register_parcel_dialog, null)
    private val editTextValue = view.findViewById<TextInputEditText>(R.id.parcelValueEditText)
    private val editTextDate = view.findViewById<TextInputEditText>(R.id.parcelDateEditText)
    private val registerParcelButton = view.findViewById<Button>(R.id.registerParcelButton)
    private val cancelButton = view.findViewById<Button>(R.id.cancelButton)
    private val alertDialog = AlertDialog.Builder(context).setCancelable(true).create()

    fun show() {
        configComponents()
        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun configComponents() {
        registerParcelButton.setOnClickListener {
            viewModel.payParcel(
                borrowingId,
                PayParcelBorrowingRequestDTO(
                    BigDecimal(editTextValue.text.toString()),
                    LocalDate.parse(editTextDate.text.toString(), DEFAULT_DATE_FORMAT).toString()
                )
            ).observe(lifecycleOwner) {
                it.let { result ->
                    when (result) {
                        is Result.Success -> {
                            Toast.makeText(
                                context,
                                "Parcela registrada com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is Result.Error -> {
                            Toast.makeText(
                                context,
                                "Erro ao registrar parcela!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
            alertDialog.dismiss()
        }
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}