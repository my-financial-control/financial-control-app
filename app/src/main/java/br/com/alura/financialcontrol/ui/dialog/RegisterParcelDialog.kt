package br.com.alura.financialcontrol.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.alura.financialcontrol.R
import com.google.android.material.textfield.TextInputEditText

class RegisterParcelDialog(
    private val context: Context, private val borrowingId: String
) {

    private val inflater = LayoutInflater.from(context)
    private val view = inflater.inflate(R.layout.register_parcel_dialog, null)
    private val editTextValue = view.findViewById<TextInputEditText>(R.id.parcelValueEditText)
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
            Toast.makeText(
                context, "Em desenvolvimento", Toast.LENGTH_SHORT
            ).show()
            alertDialog.dismiss()
        }
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}