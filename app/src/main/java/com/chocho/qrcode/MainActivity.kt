package com.chocho.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_QR_SCAN = 49374

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val qrCodeButton = findViewById<Button>(R.id.qr_code_button)
        qrCodeButton.setOnClickListener {
            val qrScanIntegrator = IntentIntegrator(this)
            qrScanIntegrator.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_QR_SCAN) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result.contents != null) {
                val qrCodeValue = result.contents
                Log.d("QRCode1", "QR code value is $qrCodeValue")
                // ...
            }
            if (result.contents != null) {
                val qrCodeValue = result.contents
                if (qrCodeValue.length == 3 && qrCodeValue.toIntOrNull() != null) {
                    val number = qrCodeValue.toInt()
                    val intent = Intent(this, NextActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid QR code value", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Scan canceled", Toast.LENGTH_SHORT).show()
            }
        }

    }
}