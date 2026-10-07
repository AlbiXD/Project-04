package com.example.project_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The MainActivity for the Movies app.
 * Launches a [MoviesFragment].
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(
            R.id.content,
            MoviesFragment(),
            null
        ).commit()
    }
}