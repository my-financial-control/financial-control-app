package br.com.alura.financialcontrol.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.defaultFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(formatter)
}
