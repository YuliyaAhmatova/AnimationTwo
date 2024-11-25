package com.example.animationtwo

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var titleMainTV: TextView
    private lateinit var imageMainIV: ImageView
    private lateinit var startShopBTN: Button
    private lateinit var main: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        imageMainIV.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.withEndAction {
            imageMainIV.animate().apply {
                duration = 2000
                rotationYBy(3600f)
            }
        }.start()

        startShopBTN.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val moveTopToBottom =
            AnimationUtils.loadAnimation(applicationContext, R.anim.move_top_to_bottom)
        titleMainTV.startAnimation(moveTopToBottom)

        val moveBottomToTop =
            AnimationUtils.loadAnimation(applicationContext, R.anim.move_bottom_to_top)
        startShopBTN.startAnimation(moveBottomToTop)

        backgroundAnimation()
    }

    private fun backgroundAnimation() {
        val animation: AnimationDrawable = main.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }

    fun init() {
        main = findViewById(R.id.main)
        titleMainTV = findViewById(R.id.titleMainTV)
        imageMainIV = findViewById(R.id.imageMainIV)
        startShopBTN = findViewById(R.id.startShopBTN)
    }
}