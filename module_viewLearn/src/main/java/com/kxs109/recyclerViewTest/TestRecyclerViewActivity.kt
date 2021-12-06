package com.kxs109.recyclerViewTest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kxs109.viewlearn.R
import kotlin.random.Random

class TestRecyclerViewActivity : AppCompatActivity() {
    var list : MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycler_view)

        findViewById<RecyclerView>(R.id.rv).run {
            adapter = SimpleRecyclerAdapter(this@TestRecyclerViewActivity, initAdapterData())
            addItemDecoration(DividerItemDecoration(this@TestRecyclerViewActivity,
                DividerItemDecoration.VERTICAL))
        }
    }

    private fun initAdapterData(): MutableList<String> {
         list  = mutableListOf<String>().apply {
            for (num in 0..99) {
                add("Sample${num}")
            }
        }
        return list!!
    }

    fun changeData(view: View) {
        list!![0]= "hello${Random.nextInt()}"
        findViewById<RecyclerView>(R.id.rv).adapter?.notifyItemChanged(0)
//        findViewById<RecyclerView>(R.id.rv).scrollToPosition(0)
    }
}