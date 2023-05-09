package com.rugulous.beanz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager

private const val DELAY_TIMER = 3000L

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        delayedOpenNextActivity();
    }

    private fun delayedOpenNextActivity(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, AddAccountActivity::class.java))
        }, DELAY_TIMER)
    }
}