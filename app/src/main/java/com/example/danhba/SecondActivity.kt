package com.example.danhba

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        try {
            val idTextView = findViewById<TextView>(R.id.id_textView)
            val nameTextView = findViewById<TextView>(R.id.name_textView)
            val phoneTextView = findViewById<TextView>(R.id.phone_textView)
            val emailTextView = findViewById<TextView>(R.id.email_textView)

            val id = intent.getIntExtra("id", 0)
            val name = intent.getStringExtra("name")
            val phone = intent.getStringExtra("phone")
            val email = intent.getStringExtra("email")

            idTextView.text = id.toString()
            nameTextView.text = name
            phoneTextView.text = phone
            emailTextView.text = email

            setResult(Activity.RESULT_OK, intent)
        } catch (ex: Exception) {
            setResult(Activity.RESULT_CANCELED)
        }
    }
}