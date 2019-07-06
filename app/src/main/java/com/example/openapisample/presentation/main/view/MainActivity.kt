package com.example.openapisample.presentation.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityMainBinding
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.common.view.showSimpleAlertDlg
import com.example.openapisample.presentation.common.view.showToast
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.detail.view.DetailActivity
import com.example.openapisample.presentation.main.viewmodel.MainViewModel
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemClickModel
import com.example.openapisample.presentation.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

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
            list.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            editSearchBox.setOnEditorActionListener { v, actionId, _ ->
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
                MsgPriority.HIGH -> showAlertDlg(it.second)
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
        DetailActivity.start(this, clickModel.id)
    }

    private fun showAlertDlg(msg: String) {
        showSimpleAlertDlg(msg)
    }

    private fun hideKeyboard(v: View) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            v.windowToken,
            0
        )
    }
}
