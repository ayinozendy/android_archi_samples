package com.gamalinda.android.poc.archisample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamalinda.android.poc.archisample.R
import com.gamalinda.android.poc.archisample.databinding.ActivityMainBinding
import com.gamalinda.android.poc.archisample.ui.playlist.PlaylistFragment

class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = PlaylistFragment.createInstance()
            val tx = supportFragmentManager.beginTransaction()
            tx.add(R.id.fragmentContainerView, fragment, PlaylistFragment.TX_TAG)
            tx.commit()
        }
    }
}
