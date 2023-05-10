package com.rugulous.beanz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

private const val DELAY_TIMER = 3000L

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWindow()
        setContentView(R.layout.activity_main)

        delayedOpenNextActivity();
    }

    private fun initWindow(){
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.money)
    }

    private fun delayedOpenNextActivity(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, AddAccountActivity::class.java))
            finish()
        }, DELAY_TIMER)
    }
}