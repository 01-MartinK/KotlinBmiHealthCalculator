package com.app.bmicalculator

import android.app.usage.UsageEvents
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val underweightBmiValue = 18.6
    val overweightBmiValue = 25
    val obeseBmiValue = 29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main)

        val calcButton: Button = findViewById(R.id.CalculateBmiButton)
        calcButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val HeightText = findViewById<EditText>(R.id.HeightCalculationField).text.toString()
                val WeightText = findViewById<EditText>(R.id.WeightCalculationField).text.toString()

                var AnswerText = findViewById<TextView>(R.id.BmiCalcAnswerText)

                var bmi = calculateBmi(convertStringToFloat(HeightText), convertStringToFloat(WeightText))

                AnswerText.text = Math.round(bmi).toString()
            }
        })
    }

    fun calculateBmi(height: Float, weight: Float): Float {
        var bmi = weight / (height / 100 * height / 100);

        var descTextView = findViewById<TextView>(R.id.BmiCalcAnswerDesc)

        if (bmi > obeseBmiValue) {
            descTextView.text = "You are obese!"
        }
        else if (bmi > overweightBmiValue && bmi < obeseBmiValue) {
            descTextView.text = "You are fat."
        }
        else if (bmi > underweightBmiValue && bmi < overweightBmiValue) {
            descTextView.text = "Normal weight range for a person about your height."
        }
        else if (bmi < underweightBmiValue) {
            descTextView.text = "You are underweight."
        }

        return bmi;
    }

    private fun convertStringToFloat(input: String): Float {
        var errorView: TextView = findViewById(R.id.ErrorMessage);

        return if (checkForFloat(input)) {
            errorView.visibility = TextView.INVISIBLE;
            var b: Float = input.toFloat()
            b;
        }else {
            errorView.visibility = TextView.VISIBLE;
            0.0f;
        }
    }

    private fun checkForFloat(input: String): Boolean {
        try {
            input.toFloat()
        } catch (e: NumberFormatException) {
            println("failed because contains letters $e")
            return false;
        }

        return true;
    }
}