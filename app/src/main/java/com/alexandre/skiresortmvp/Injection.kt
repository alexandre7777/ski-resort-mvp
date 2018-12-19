package com.alexandre.skiresortmvp

import android.app.Application
import com.alexandre.skiresortmvp.data.db.SkiResortDao
import com.alexandre.skiresortmvp.data.db.SkiResortDatabase
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListInteractor
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListPresenter
import java.util.concurrent.Executors

object  Injection{

    private fun provideSkiResortDao(application: Application) : SkiResortDao {
        val database = SkiResortDatabase.getInstance(application)
        return database.skiResortDao()
    }

    private fun providePresenter(view: SkiResortList.View) : SkiResortListPresenter {
        return SkiResortListPresenter(view)
    }

    fun provideSkiResortListInteractor(application: Application, view: SkiResortList.View) : SkiResortListInteractor {
        return SkiResortListInteractor(providePresenter(view),
            SkiResortListService.create(),
            provideSkiResortDao(application),
            Executors.newSingleThreadExecutor())
    }
}