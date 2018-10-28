package com.alexandre.skiresortmvp

import android.app.Application
import com.alexandre.skiresortmvp.data.db.SkiResortDao
import com.alexandre.skiresortmvp.data.db.SkiResortDatabase
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListInteractor
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListPresenter
import java.util.concurrent.Executors

object  Injection{

    private fun provideSkiResortDao(application: Application) : SkiResortDao {
        val database = SkiResortDatabase.getInstance(application)
        return database.skiResortDao()
    }

    fun provideSkiResortListInteractor(application: Application, presenter: SkiResortListPresenter) : SkiResortListInteractor {
        return SkiResortListInteractor(presenter,
            SkiResortListService.create(),
            provideSkiResortDao(application),
            Executors.newSingleThreadExecutor())
    }
}