package com.example.openapisample.presentation.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityTestMainBinding

class TestMainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TestMainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTestMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_main)
        binding.btnMerged.setOnClickListener {
            MergedActivity.start(this@TestMainActivity)
        }
        binding.btnNormal.setOnClickListener {
            NormalActivity.start(this@TestMainActivity)
        }
    }
}