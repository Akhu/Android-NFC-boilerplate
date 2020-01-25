package com.pickle.nfcboilerplateapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.isEnabled = false
        launchScanButton.text = "READ NFC TAG"

        launchScanButton.setOnClickListener {
            val intent = Intent(this, NFCScanActivity::class.java)
            intent.putExtra(NFCScanActivity.EXTRA_NFC_MODE, NFCScanActivity.NFC_MODE_READ)
            startActivityForResult(intent, 1001)
        }

        switchNfcMode.setOnCheckedChangeListener { compoundButton, state ->
            when(state){
                false -> {
                    editText.isEnabled = false
                    launchScanButton.text = "READ NFC TAG"
                    launchScanButton.setOnClickListener {
                        val intent = Intent(this, NFCScanActivity::class.java)
                        intent.putExtra(NFCScanActivity.EXTRA_NFC_MODE, NFCScanActivity.NFC_MODE_READ)
                        startActivityForResult(intent, 1001)
                    }
                }
                true -> {
                    editText.isEnabled = true
                    launchScanButton.text = "WRITE NFC TAG"
                    launchScanButton.setOnClickListener {
                        val intent = Intent(this, NFCScanActivity::class.java)
                        intent.putExtra(NFCScanActivity.EXTRA_NFC_MODE, NFCScanActivity.NFC_MODE_WRITE)
                        intent.putExtra(NFCScanActivity.EXTRA_NFC_DATA, editText.text.toString())
                        startActivityForResult(intent, 1001)
                    }
                }
            }
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
