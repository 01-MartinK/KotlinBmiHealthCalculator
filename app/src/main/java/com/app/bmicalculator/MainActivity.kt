package com.app.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main)
    }

    fun CalculateBmi(height: Float, weight: Float): Float {
        var bmi = weight / (height * height);
        return bmi;
    }

    fun ConvertStringToFloat(input: String): Float {
        return if (CheckForFloat(input)) {
            var b: Float = ConvertStringToFloat(input);
            b;
        }else {
            0.0f;
        }
    }

    fun CheckForFloat(input: String): Boolean {
        try {
            ConvertStringToFloat(input)
        } catch (e: NullPointerException) {
            println("failed because contains letters")
            return false;
        }

        return true;
    }
}