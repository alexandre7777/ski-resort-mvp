package com.alexandre.skiresortmvp.domain

import com.alexandre.skiresortmvp.data.network.model.SkiResort

fun toViewModel(skiResortList: List<SkiResort>) :
        List<com.alexandre.skiresortmvp.domain.SkiResort> {
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

fun toDbModel(skiResortList: List<SkiResort>) :
        List<com.alexandre.skiresortmvp.data.db.model.SkiResort> {
    return skiResortList.map {
        com.alexandre.skiresortmvp.data.db.model.SkiResort(
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

fun toViewModelFromDb(skiResortList: List<com.alexandre.skiresortmvp.data.db.model.SkiResort>) :
        List<com.alexandre.skiresortmvp.domain.SkiResort> {
    return skiResortList.map {
        com.alexandre.skiresortmvp.domain.SkiResort(
            it.skiResortId,
            it.name,
            it.country,
            it.mountainRange,
            it.slopeKm,
            it.lifts,
            it.slopes,
            it.isFav
        )
    }
}