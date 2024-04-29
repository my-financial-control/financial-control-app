package br.com.alura.financialcontrol.ui.components

import android.widget.EditText
import androidx.fragment.app.FragmentManager
import br.com.alura.financialcontrol.utils.DEFAULT_DATE_FORMAT
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.ZoneId

fun buildDatePicker(
    editText: EditText,
    supportFragmentManager: FragmentManager,
    hint: String
): MaterialDatePicker<Long> {
    val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(hint).build()
    editText.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            editText.clearFocus()
            datePicker.show(
                supportFragmentManager,
                datePicker.toString()
            )
        }
    }
    datePicker.addOnPositiveButtonClickListener { selection ->
        val localDate = Instant.ofEpochMilli(selection).atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1)
        editText.setText(localDate.format(DEFAULT_DATE_FORMAT))
    }
    return datePicker
}
