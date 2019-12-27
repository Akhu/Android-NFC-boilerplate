package com.pickle.nfcboilerplateapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchScanButton.setOnClickListener {
            val intent = Intent(this, NFCScanActivity::class.java)
            startActivityForResult(intent, 1001)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1001 && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<NFCData>("data")?.let {
                Timber.d("Received data from NFC Tag :${it.data}")
                Toast.makeText(this, "Received data from NFC Tag :${it.data}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }
}
