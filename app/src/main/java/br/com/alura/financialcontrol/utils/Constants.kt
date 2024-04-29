package br.com.alura.financialcontrol.utils

import java.time.format.DateTimeFormatter
import java.util.Locale

val DEFAULT_DATE_FORMAT: DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("pt", "BR"))
