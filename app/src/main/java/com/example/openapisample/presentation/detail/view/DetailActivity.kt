package com.example.openapisample.presentation.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityDetailBinding
import com.example.openapisample.presentation.common.view.showSimpleAlertDlg
import com.example.openapisample.presentation.common.view.showToast
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.detail.viewmodel.DetailViewModel
import com.example.openapisample.presentation.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val KEY_ID = "KEY_ID"
        private const val INVALID_ID: Long = -1

        fun start(context: Context, id: Long) {
            context.startActivity(
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(KEY_ID, id)
                }
            )
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel {
        parametersOf(intent?.getLongExtra(KEY_ID, INVALID_ID) ?: INVALID_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.viewModel = viewModel
        setSupportActionBar(binding.toolbar)
        initObserver()

        viewModel.requestDetail()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObserver() {
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