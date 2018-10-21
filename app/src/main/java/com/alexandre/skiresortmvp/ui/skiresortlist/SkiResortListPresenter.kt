package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter

class SkiResortListPresenter(private val view: SkiResortList.View) : Presenter {

    override fun load() {
        view.display()
    }
}