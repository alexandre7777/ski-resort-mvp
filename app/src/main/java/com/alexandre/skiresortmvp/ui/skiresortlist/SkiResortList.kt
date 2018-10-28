package com.alexandre.skiresortmvp.ui.skiresortlist

import com.alexandre.skiresortmvp.domain.SkiResort

interface SkiResortList {

    interface View {
        fun display(skiResortList: List<SkiResort>)
    }

    interface Presenter {
        fun load()
    }

    interface Interactor {
        fun load()
    }

    interface InteractorCallback {
        fun callback(skiResortList: List<SkiResort>)
    }
}