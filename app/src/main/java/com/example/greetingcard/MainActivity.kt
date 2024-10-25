package com.example.greetingcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menghubungkan komponen dari layout
        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val createButton = findViewById<Button>(R.id.buttonCreateCard)

        // Membuat kartu ucapan saat tombol ditekan
        createButton.setOnClickListener {
            val name = nameEditText.text.toString()

            // Membuat Intent untuk memulai GreetingCardActivity
            val intent = Intent(this, GreetingCardActivity::class.java)
            intent.putExtra("RECIPIENT_NAME", name) // Mengirim nama penerima
            startActivity(intent)
        }
    }
}
