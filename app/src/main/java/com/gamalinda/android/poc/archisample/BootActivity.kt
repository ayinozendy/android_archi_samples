package com.gamalinda.android.poc.archisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamalinda.android.poc.archisample.ui.MainActivity

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = MainActivity.createIntent(this)
        startActivity(intent)
        finish()
    }
}
