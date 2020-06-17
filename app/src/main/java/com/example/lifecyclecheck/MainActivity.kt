package com.example.lifecyclecheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate state:" + lifecycle.currentState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "on " +
                "Start state:" + lifecycle.currentState)
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume state:" + lifecycle.currentState)
    }

    override fun onPostResume() { //onPostResume()はonResumeのあとに呼ばれる。
        super.onPostResume()
        Log.d("MainActivity", "onPostResume state:" + lifecycle.currentState)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause state:" + lifecycle.currentState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy state:" + lifecycle.currentState)
    }
}