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
        greetingTextView.text = getString(R.string.greeting_text, recipientName)

        // Tombol Kembali
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.text = getString(R.string.button_back)
        buttonBack.setOnClickListener {
            finish() // Menutup aktivitas saat ini dan kembali ke MainActivity
        }

        // Tombol Download Card
        val buttonDownload = findViewById<Button>(R.id.buttonDownload)
        buttonDownload.text = getString(R.string.button_download)
        buttonDownload.setOnClickListener {
            downloadCard()
        }

        // Tombol Share
        val buttonShare = findViewById<Button>(R.id.buttonShare)
        buttonShare.text = getString(R.string.button_share)
        buttonShare.setOnClickListener {
            shareCard(greetingTextView.text.toString())
        }
    }

    private fun downloadCard() {
        val cardView = findViewById<View>(R.id.textViewGreeting)
        val bitmap = Bitmap.createBitmap(cardView.width, cardView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        cardView.draw(canvas)

        try {
            val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(filePath, "greeting_card.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(this, getString(R.string.download_success), Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, getString(R.string.download_failure), Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareCard(greetingText: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, greetingText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_with)))
    }
}
