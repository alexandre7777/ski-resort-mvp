package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.data.network.model.SkiResort
import com.alexandre.skiresortmvp.data.network.requestSkiResort

class SkiResortListInteractor(private val presenter: SkiResortListPresenter, private val skiResortListService: SkiResortListService) : SkiResortList.Interactor {

    override fun load(){
        requestSkiResort(skiResortListService, {
                skiResorts ->
                presenter.callback(toViewModel(skiResorts))
            }, { error ->

            })
    }

    private fun toViewModel(skiResortList: List<SkiResort>) : List<com.alexandre.skiresortmvp.domain.SkiResort> {
        return skiResortList.map {
            com.alexandre.skiresortmvp.domain.SkiResort(
                it.skiResortId,
                it.name,
                it.country,
                it.mountainRange,
                it.slopeKm,
                it.lifts,
                it.slopes
            )
        }
    }
}