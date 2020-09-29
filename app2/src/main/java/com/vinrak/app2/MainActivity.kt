package com.vinrak.app2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val mTag = "MainActivity"

    companion object {
        private const val ONE_THOUSAND = 4
        private const val TEN_THOUSAND = 5
        private const val ONE_LAKH = 6
        private const val TEN_LAKH = 7
        private const val ONE_CR = 8
        private const val TEN_CR = 9
        private const val HUNDRED_CR = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formattedEditText()
        getFormattedValue()

    }

    private fun getFormattedValue() {
        findViewById<Button>(R.id.buttonFormat).setOnClickListener {
            try {

                findViewById<TextView>(R.id.tvFormatValue).text =
                    getRupeesFormat(findViewById<EditText>(R.id.etValue).text!!.toString(),mTag)
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid Value = ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formattedEditText() {
        val etAmount: EditText = findViewById(R.id.etAmount)
        etAmount.maxWidth = 14
        etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                etAmount.removeTextChangedListener(this)
                when (s!!.toString().replace(",", "").length) {
                    1, 2, 3 -> {
                        val format = etAmount.text.toString()
                        Log.d(mTag, "basic format = ${etAmount.text}")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    ONE_THOUSAND -> {
                        val format = DecimalFormat("#,###").format(
                            etAmount.text.toString().replace(",", "").toInt()
                        )
                        Log.d(mTag, "thousand format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    TEN_THOUSAND -> {
                        val format =
                            DecimalFormat("##,###").format(
                                etAmount.text.toString().replace(",", "").toInt()
                            )
                        Log.d(mTag, "ten thousand format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    ONE_LAKH -> {
                        val format =
                            DecimalFormat("#,##,###").format(
                                etAmount.text.toString().replace(",", "").toInt()
                            )
                        Log.d(mTag, "one lakh format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    TEN_LAKH -> {
                        val format =
                            DecimalFormat("#,##,##,###").format(
                                etAmount.text.toString().replace(",", "").toInt()
                            )
                        Log.d(mTag, "ten lakh format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    ONE_CR -> {
                        val format =
                            DecimalFormat("#,##,##,###").format(
                                etAmount.text.toString().replace(",", "").toInt()
                            )
                        Log.d(mTag, "one crore format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    TEN_CR -> {
                        val format =
                            DecimalFormat("##,##,##,###").format(
                                etAmount.text.toString().replace(",", "").toInt()
                            )
                        Log.d(mTag, "ten crore format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    HUNDRED_CR -> {
                        val format =
                            DecimalFormat("###,##,##,###").format(
                                etAmount.text.toString().replace(",", "").toBigInteger()
                            )
                        Log.d(mTag, "hundred crore format = $format")
                        etAmount.setText(format)
                        etAmount.setSelection(etAmount.text.length)
                    }
                    else -> {
                        Log.d(mTag, "wrong option selected")
                        etAmount.setText(etAmount.text.toString())
                        etAmount.setSelection(etAmount.text.length)
                    }
                }
                etAmount.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

}