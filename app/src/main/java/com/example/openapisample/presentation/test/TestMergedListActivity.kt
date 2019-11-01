package com.example.openapisample.presentation.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityTestListBinding

class TestMergedListActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestListBinding
    val adapter = MergedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_list)
        binding.list.adapter = adapter
    }
}