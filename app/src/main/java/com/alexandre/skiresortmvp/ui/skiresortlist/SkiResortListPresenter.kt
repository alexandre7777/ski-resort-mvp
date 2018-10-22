package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter

class SkiResortListPresenter(private val view: SkiResortList.View) : Presenter {

    private val interactor: SkiResortListInteractor = SkiResortListInteractor(this)

    override fun load() {
        interactor.load()
    }

    fun callback() {
        view.display()
    }
}