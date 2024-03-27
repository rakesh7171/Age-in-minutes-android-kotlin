package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.icu.util.Calendar.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDateListener(view)


        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDateListener(view: View) {
        val myCalendar = getInstance()
        val year = myCalendar.get(YEAR)
        val month = myCalendar.get(MONTH)
        val day = myCalendar.get(DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "the choosen year is $selectedYear,the month is $selectedMonth and the day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val textArea1 = findViewById<TextView>(R.id.tvSelectedDate)
                val textArea = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                textArea1.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinute = currentDate!!.time / 60000
                val diff = currentDateInMinute - selectedDateInMinutes
                textArea.text = diff.toString()


            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }


}


