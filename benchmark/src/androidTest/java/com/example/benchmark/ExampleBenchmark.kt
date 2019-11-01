package com.example.benchmark

import android.view.View
import android.view.ViewGroup
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.ui.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.random.Random

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val normalActivityRule = ActivityTestRule(TestNormalListActivity::class.java)

    private fun getRandomList(): List<TweetItemViewModel> {
        return mutableListOf<TweetItemViewModel>().apply {
            for (i in 0..100000) {
                add(
                    TweetItemViewModel(
                        id = 0,
                        imageUrl = "",
                        contents = "contents ${Random.nextInt()}",
                        userProfileImageUrl = "",
                        userName = "userName ${Random.nextInt()}",
                        retweetCount = Random.nextInt(),
                        favoriteCount = Random.nextInt(),
                        createdAt = Date().toString(),
                        eventSender = object : IEventSender {
                            override fun click(clickModel: IClickModel) {
                            }
                        }
                    )
                )
            }
        }
    }


    @Before
    fun setup() {
        val data = getRandomList()
        normalActivityRule.runOnUiThread {
            normalActivityRule.activity.adapter.submitList(null)
            normalActivityRule.activity.adapter.submitList(data)
        }
    }

    private fun ViewGroup.getLastChild(): View = getChildAt(childCount - 1)

    @UiThreadTest
    @Test
    fun normalListScroll() {
        val recyclerView = normalActivityRule.activity.list
        Assert.assertTrue("RecyclerView expected to have children", recyclerView.childCount > 0)

        // RecyclerView has children, itsitems are attached, bound, and have gone through layout.
        // Ready to benchmark.
        benchmarkRule.measureRepeated {
            // Scroll RecyclerView by one item
            // this will synchronously execute: attach / detach(old item) / bind / layout
            recyclerView.scrollBy(0, recyclerView.getLastChild().height)
        }
    }

//    @Test
//    fun log() {
//        benchmarkRule.measureRepeated {
//            Log.d("LogBenchmark", "the cost of writing this log method will be measured")
//        }
//    }
}
