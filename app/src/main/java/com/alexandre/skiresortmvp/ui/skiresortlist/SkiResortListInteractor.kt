package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.data.db.SkiResortDao
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.data.network.model.SkiResort
import com.alexandre.skiresortmvp.data.network.requestSkiResort
import com.alexandre.skiresortmvp.domain.toDbModel
import com.alexandre.skiresortmvp.domain.toViewModel
import com.alexandre.skiresortmvp.domain.toViewModelFromDb
import java.util.concurrent.Executor

class SkiResortListInteractor(
    private val presenter: SkiResortList.Presenter,
    private val skiResortListService: SkiResortListService,
    private val skiResortDao: SkiResortDao,
    private val ioExecutor: Executor) : SkiResortList.Interactor {

    private var networkSkiResorts: List<SkiResort>? = null

    override fun loadSkiResortList(){
        //get data from db
        ioExecutor.execute {
            networkSkiResorts?.let {
                presenter.responseSkiResortList(toViewModel(it, skiResortDao.getAllSkiResorts()))
            } ?: presenter.responseSkiResortList(toViewModelFromDb(skiResortDao.getAllSkiResorts()))

        }

        //get data from network
        requestSkiResort(skiResortListService, {
                skiResorts ->
                networkSkiResorts = skiResorts
                ioExecutor.execute {
                    skiResortDao.insertAll(prepareInsertWithFavStatus(toDbModel(skiResorts)))
                    presenter.responseSkiResortList(toViewModel(skiResorts, skiResortDao.getAllSkiResorts()))
                }
            }, { error ->

            })
    }

    override fun toggleFav(skiResort: com.alexandre.skiresortmvp.domain.SkiResort){
        ioExecutor.execute {
            skiResortDao.updateFav(skiResort.skiResortId, !skiResort.isFav)
            networkSkiResorts?.let {
                presenter.responseSkiResortList(toViewModel(it, skiResortDao.getAllSkiResorts()))
            } ?: presenter.responseSkiResortList(toViewModelFromDb(skiResortDao.getAllSkiResorts()))
        }
    }

    private fun prepareInsertWithFavStatus(skiResorts : List<com.alexandre.skiresortmvp.data.db.model.SkiResort>): List<com.alexandre.skiresortmvp.data.db.model.SkiResort> {
        val mutableIterator = skiResorts.iterator()

        // iterator() extension is called here
        for (skiResort in mutableIterator) {
            skiResort.isFav = skiResortDao.isFav(skiResort.skiResortId)
        }
        return skiResorts
    }
}