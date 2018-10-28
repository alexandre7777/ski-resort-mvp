package com.alexandre.skiresortmvp.ui.skiresortlist

import android.app.Application
import com.alexandre.skiresortmvp.Injection
import com.alexandre.skiresortmvp.domain.SkiResort
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter

class SkiResortListPresenter(private val view: SkiResortList.View, application: Application) :
    Presenter,
    SkiResortList.InteractorCallback {

    private val interactor = Injection.provideSkiResortListInteractor(application, this)

    override fun requestSkiResortList() {
        interactor.loadSkiResortList()
    }

    override fun responseSkiResortList(skiResortList: List<SkiResort>) {
        view.displaySkiResortList(skiResortList)
    }
}