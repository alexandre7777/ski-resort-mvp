package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.domain.SkiResort

interface SkiResortList {

    interface View {
        fun displaySkiResortList(skiResortList: List<SkiResort>)
    }

    interface Presenter {
        fun responseSkiResortList(skiResortList: List<SkiResort>)
    }

    interface Interactor {
        fun loadSkiResortList()
        fun toggleFav(skiResort: com.alexandre.skiresortmvp.domain.SkiResort)
    }
}