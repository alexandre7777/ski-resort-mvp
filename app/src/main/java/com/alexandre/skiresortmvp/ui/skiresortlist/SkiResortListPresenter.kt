package com.alexandre.skiresortmvp.ui.skiresortlist

import android.app.Application
import com.alexandre.skiresortmvp.data.db.SkiResortDatabase
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.domain.SkiResort
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList.Presenter
import java.util.concurrent.Executors

class SkiResortListPresenter(private val view: SkiResortList.View, private val application: Application) : Presenter {

    private val interactor: SkiResortListInteractor = SkiResortListInteractor(this,
        SkiResortListService.create(),
        SkiResortDatabase.getInstance(application).skiResortDao(),
        Executors.newSingleThreadExecutor())

    override fun load() {
        interactor.load()
    }

    fun callback(skiResortList: List<SkiResort>) {
        view.display(skiResortList)
    }
}