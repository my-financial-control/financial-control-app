package br.com.alura.financialcontrol.utils

import java.time.Month

fun monthFromPtBr(monthPtBr: String): Month {
    return when (monthPtBr.uppercase()) {
        "JANEIRO" -> Month.JANUARY
        "FEVEREIRO" -> Month.FEBRUARY
        "MARÇO" -> Month.MARCH
        "MARCO" -> Month.MARCH
        "ABRIL" -> Month.APRIL
        "MAIO" -> Month.MAY
        "JUNHO" -> Month.JUNE
        "JULHO" -> Month.JULY
        "AGOSTO" -> Month.AUGUST
        "SETEMBRO" -> Month.SEPTEMBER
        "OUTUBRO" -> Month.OCTOBER
        "NOVEMBRO" -> Month.NOVEMBER
        "DEZEMBRO" -> Month.DECEMBER
        else -> throw IllegalArgumentException()
    }
}
