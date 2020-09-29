package com.vinrak.app2

import android.util.Log
import java.text.DecimalFormat

private const val ONE_THOUSAND = 4
private const val TEN_THOUSAND = 5
private const val ONE_LAKH = 6
private const val TEN_LAKH = 7
private const val ONE_CR = 8
private const val TEN_CR = 9
private const val HUNDRED_CR = 10

fun getRupeesFormat(it: String, mTag: String): String {

    var beforeDot: String = ""
    var afterDot: String = ""
    if (it.contains(".")) {
        afterDot = it.substring(it.indexOf("."))
        beforeDot = it.replace(afterDot, "")
        Log.d(mTag, "it = $it")
    } else {
        afterDot = ""
        beforeDot = it
    }


    return when (beforeDot.length) {
        1, 2, 3 -> {
            it
        }
        ONE_THOUSAND -> {
            //when Locale.ENGLISH from DecimalFormatSymbols() parameter is removed, then it worked on lower end devices
            DecimalFormat("#,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        TEN_THOUSAND -> {
            DecimalFormat("##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        ONE_LAKH -> {
            DecimalFormat("#,##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        TEN_LAKH -> {
            DecimalFormat("#,##,##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        ONE_CR -> {
            DecimalFormat("#,##,##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        TEN_CR -> {
            DecimalFormat("##,##,##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        HUNDRED_CR -> {
            DecimalFormat("###,##,##,###").format(beforeDot.toBigInteger()).plus(afterDot)
        }
        else -> {
            it
        }
    }
}