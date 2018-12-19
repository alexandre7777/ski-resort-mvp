package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.data.db.SkiResortDao
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.data.network.requestSkiResort
import com.alexandre.skiresortmvp.domain.toDbModel
import com.alexandre.skiresortmvp.domain.toViewModelFromDb
import java.util.concurrent.Executor

class SkiResortListInteractor(
    private val presenter: SkiResortList.InteractorCallback,
    private val skiResortListService: SkiResortListService,
    private val skiResortDao: SkiResortDao,
    private val ioExecutor: Executor) : SkiResortList.Interactor {

    override fun loadSkiResortList(){
        //get data from db
        ioExecutor.execute {
            presenter.responseSkiResortList(toViewModelFromDb(skiResortDao.getAllSkiResorts()))
        }

        //get data from network
        requestSkiResort(skiResortListService, {
                skiResorts ->
                ioExecutor.execute {
                    skiResortDao.insertAll(prepareInsertWithFavStatus(toDbModel(skiResorts)))
                    presenter.responseSkiResortList(toViewModelFromDb(skiResortDao.getAllSkiResorts()))
                }
            }, { error ->

            })
    }

    override fun toggleFav(skiResort: com.alexandre.skiresortmvp.domain.SkiResort){
        ioExecutor.execute {
            skiResortDao.updateFav(skiResort.skiResortId, !skiResort.isFav)
            presenter.responseSkiResortList(toViewModelFromDb(skiResortDao.getAllSkiResorts()))
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