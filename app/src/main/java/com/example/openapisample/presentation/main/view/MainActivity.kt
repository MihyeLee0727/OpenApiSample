package com.example.openapisample.presentation.main.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityMainBinding
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.main.viewmodel.MainViewModel
import com.example.openapisample.presentation.main.viewmodel.MsgPriority
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemClickModel
import com.example.openapisample.presentation.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter()
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        initObserver()
    }

    private fun initView() {
        with(binding) {
            viewModel = this@MainActivity.viewModel
            list.adapter = this@MainActivity.adapter
            editSearchBox.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard(v)
                    this@MainActivity.viewModel.search()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun initObserver() {
        observe(viewModel.event.click) {
            handleClick(it)
        }
        observe(viewModel.event.searchResult) {
            adapter.clear()
            adapter.addAll(it)
        }
        observe(viewModel.event.message) {
            when (it.first) {
                MsgPriority.HIGH,
                MsgPriority.LOW -> showToast(it.second)
            }
        }
    }

    private fun handleClick(clickModel: IClickModel) {
        when (clickModel) {
            is TweetItemClickModel -> showDetail(clickModel)
        }
    }

    private fun showDetail(clickModel: TweetItemClickModel) {
        // TODO show detail activity
        showToast("TODO show detial")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(v: View) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            v.windowToken,
            0
        )
    }
}
