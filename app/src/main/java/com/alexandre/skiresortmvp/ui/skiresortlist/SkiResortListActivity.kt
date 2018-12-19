package com.alexandre.skiresortmvp.ui.skiresortlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alexandre.skiresortmvp.Injection
import com.alexandre.skiresortmvp.R
import com.alexandre.skiresortmvp.domain.SkiResort
import kotlinx.android.synthetic.main.activity_ski_resort_list.*

class SkiResortListActivity : AppCompatActivity(), SkiResortList.View {
    private var interactor: SkiResortListInteractor? = null
    private var adapter = SkiResortAdapter { view: View?, skiResort: SkiResort ->
        interactor?.toggleFav(skiResort)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ski_resort_list)

        interactor = Injection.provideSkiResortListInteractor(application, this)
        interactor?.loadSkiResortList()

        initAdapter()
    }

    private fun initAdapter(){
        list.layoutManager = LinearLayoutManager(applicationContext)
        list.adapter = adapter
    }

    override fun displaySkiResortList(skiResortList: List<SkiResort>) {
        adapter.submitList(skiResortList)
    }

    override fun onDestroy() {
        super.onDestroy()
        interactor = null
    }
}
