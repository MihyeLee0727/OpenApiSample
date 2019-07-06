package com.example.openapisample.presentation.intro.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityIntroBinding
import com.example.openapisample.presentation.common.view.showSimpleAlertDlg
import com.example.openapisample.presentation.common.view.showToast
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.intro.viewmodel.IntroViewModel
import com.example.openapisample.presentation.main.view.MainActivity
import com.example.openapisample.presentation.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel: IntroViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        binding.viewModel = viewModel
        initObserver()

        viewModel.requestToken()
    }

    private fun initObserver() {
        observe(viewModel.event.processDone) {
            MainActivity.start(this)
            finish()
        }
        observe(viewModel.event.message) {
            when (it.first) {
                MsgPriority.HIGH -> showAlertDlg(it.second)
                MsgPriority.LOW -> showToast(it.second)
            }
        }
    }

    private fun showAlertDlg(msg: String) {
        showSimpleAlertDlg(msg)
    }
}