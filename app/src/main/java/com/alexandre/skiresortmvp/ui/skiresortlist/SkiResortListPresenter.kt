package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.domain.SkiResort
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter

class SkiResortListPresenter(private val view: SkiResortList.View) :
    Presenter,
    SkiResortList.InteractorCallback {

    override fun responseSkiResortList(skiResortList: List<SkiResort>) {
        view.displaySkiResortList(skiResortList)
    }
}