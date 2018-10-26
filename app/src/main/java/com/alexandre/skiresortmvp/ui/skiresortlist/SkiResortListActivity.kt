package com.alexandre.skiresortmvp.ui.skiresortlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alexandre.skiresortmvp.R
import com.alexandre.skiresortmvp.domain.SkiResort

class SkiResortListActivity : AppCompatActivity(), SkiResortList.View {

    private lateinit var presenter: SkiResortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ski_resort_list)

        presenter = SkiResortListPresenter(this)
        presenter.load()
    }

    override fun display(skiResortList: List<SkiResort>) {
        Toast.makeText(this, "loaded" + skiResortList.size, Toast.LENGTH_SHORT).show()
    }
}
