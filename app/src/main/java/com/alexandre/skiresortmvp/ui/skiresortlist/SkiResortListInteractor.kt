package com.alexandre.skiresortmvp.ui.skiresortlist

class SkiResortListInteractor(private val presenter: SkiResortListPresenter) : SkiResortList.Interactor {

    override fun load(){
        presenter.callback()
    }
}