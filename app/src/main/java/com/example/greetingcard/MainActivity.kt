package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menghubungkan komponen dari layout
        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val createButton = findViewById<Button>(R.id.buttonCreateCard)

        // Membuat kartu ucapan saat tombol ditekan
        createButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()

            // Memeriksa apakah input kosong
            if (name.isEmpty()) {
                // Menampilkan toast jika nama tidak diisi
                Toast.makeText(this, getString(R.string.empty_name_toast), Toast.LENGTH_SHORT).show()
            } else {
                // Membuat Intent untuk memulai GreetingCardActivity
                val intent = Intent(this, GreetingCardActivity::class.java)
                intent.putExtra("RECIPIENT_NAME", name)
                startActivity(intent)
            }
        }
    }
}
