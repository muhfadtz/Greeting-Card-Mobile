package com.example.greetingcard

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class GreetingCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting_card)

        // Menerima nama penerima dari MainActivity
        val recipientName = intent.getStringExtra("RECIPIENT_NAME")

        // Menghubungkan komponen TextView dari layout
        val greetingTextView = findViewById<TextView>(R.id.textViewGreeting)
        greetingTextView.text = "Selamat Hari Spesial, $recipientName! Semoga harimu menyenangkan!"

        // Tombol Kembali
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish() // Menutup aktivitas saat ini dan kembali ke MainActivity
        }

        // Tombol Download Card
        val buttonDownload = findViewById<Button>(R.id.buttonDownload)
        buttonDownload.setOnClickListener {
            downloadCard()
        }
    }

    private fun downloadCard() {
        // View yang ingin disimpan sebagai gambar
        val cardView = findViewById<View>(R.id.textViewGreeting)

        // Membuat bitmap dari tampilan
        val bitmap = Bitmap.createBitmap(cardView.width, cardView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        cardView.draw(canvas)

        // Menyimpan gambar ke galeri
        try {
            val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(filePath, "greeting_card.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(this, "Kartu ucapan berhasil disimpan", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal menyimpan kartu ucapan", Toast.LENGTH_SHORT).show()
        }
    }
}
