package com.example.greetingcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GreetingCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting_card)

        // Menerima nama penerima dari MainActivity
        val recipientName = intent.getStringExtra("RECIPIENT_NAME")

        // Menghubungkan komponen TextView dari layout
        val greetingTextView = findViewById<TextView>(R.id.textViewGreeting)

        // Menampilkan pesan kartu ucapan
        greetingTextView.text = "Selamat Hari Spesial, $recipientName! Semoga harimu menyenangkan!"
    }
}
