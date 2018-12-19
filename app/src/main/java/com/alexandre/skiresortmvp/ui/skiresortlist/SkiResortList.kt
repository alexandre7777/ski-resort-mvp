package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.domain.SkiResort

interface SkiResortList {

    interface View {
        fun displaySkiResortList(skiResortList: List<SkiResort>)
    }

    interface Presenter {
    }

    interface Interactor {
        fun loadSkiResortList()
    }

    interface InteractorCallback {
        fun responseSkiResortList(skiResortList: List<SkiResort>)
    }
}