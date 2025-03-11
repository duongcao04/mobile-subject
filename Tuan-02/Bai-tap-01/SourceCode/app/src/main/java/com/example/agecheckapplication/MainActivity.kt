package com.example.agecheckapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun onButtonClick(view: View) {
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        val name = editTextName.text.toString()
        val checkAge = this.checkGeneration(editTextAge.text.toString().toInt())
        Toast.makeText(this, "${name}:::${checkAge}", Toast.LENGTH_SHORT).show()
    }

    fun checkGeneration(age: Int): String{
        when (age) {
            in 0..2 -> return "Em bé"
            in 2..6 -> return "Trẻ em"
            in 6..65 -> return "Người lớn"
            else -> return "Người già"
        }
    }
}