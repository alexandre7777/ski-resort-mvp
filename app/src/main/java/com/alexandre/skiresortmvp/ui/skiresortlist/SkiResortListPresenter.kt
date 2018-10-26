package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.domain.SkiResort
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter

class SkiResortListPresenter(private val view: SkiResortList.View) : Presenter {

    private val interactor: SkiResortListInteractor = SkiResortListInteractor(this, SkiResortListService.create())

    override fun load() {
        interactor.load()
    }

    fun callback(skiResortList: List<SkiResort>) {
        view.display(skiResortList)
    }
}