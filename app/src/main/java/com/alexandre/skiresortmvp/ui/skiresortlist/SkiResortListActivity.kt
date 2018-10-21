package com.alexandre.skiresortmvp.ui.skiresortlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alexandre.skiresortmvp.R

class SkiResortListActivity : AppCompatActivity(), SkiResortList.View {

    private lateinit var presenter: SkiResortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ski_resort_list)

        presenter = SkiResortListPresenter(this)
        presenter.load()
    }

    override fun display() {
        Toast.makeText(this, "loaded", Toast.LENGTH_SHORT).show()
    }
}
