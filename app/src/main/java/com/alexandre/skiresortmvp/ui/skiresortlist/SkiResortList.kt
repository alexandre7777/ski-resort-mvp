package com.alexandre.skiresortmvp.ui.skiresortlist

interface SkiResortList {

    interface View {
        fun display()
    }

    interface Presenter {
        fun load()
    }

    interface Interactor {
        fun load()
    }
}