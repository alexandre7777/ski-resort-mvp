package com.alexandre.skiresortmvp.ui.skiresortlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alexandre.skiresortmvp.R
import com.alexandre.skiresortmvp.domain.SkiResort
import kotlinx.android.synthetic.main.activity_ski_resort_list.*

class SkiResortListActivity : AppCompatActivity(), SkiResortList.View {

    private lateinit var presenter: SkiResortListPresenter

    private var adapter = SkiResortAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ski_resort_list)

        presenter = SkiResortListPresenter(this, application)
        presenter.requestSkiResortList()

        initAdapter()
    }

    private fun initAdapter(){
        list.layoutManager = LinearLayoutManager(applicationContext)
        list.adapter = adapter
    }

    override fun displaySkiResortList(skiResortList: List<SkiResort>) {
        adapter.submitList(skiResortList)
    }
}
