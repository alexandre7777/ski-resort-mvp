package com.alexandre.skiresortmvp.domain

import com.alexandre.skiresortmvp.R

fun toViewModel(skiResortList: List<com.alexandre.skiresortmvp.data.network.model.SkiResort>) :
        List<SkiResort> {
    return skiResortList.map {
        SkiResort(
            it.skiResortId,
            it.name,
            it.country,
            it.mountainRange,
            it.slopeKm,
            it.lifts,
            it.slopes,
            weather = getDrawableForString(it.weather)
        )
    }
}

fun toViewModel(skiResortListService: List<com.alexandre.skiresortmvp.data.network.model.SkiResort>,
                skiResortListDb: List<com.alexandre.skiresortmvp.data.db.model.SkiResort>) :
        List<SkiResort> {
    return skiResortListService.map {
        SkiResort(
            it.skiResortId,
            it.name,
            it.country,
            it.mountainRange,
            it.slopeKm,
            it.lifts,
            it.slopes,
            getFavFromList(skiResortListDb, it.skiResortId),
            weather = getDrawableForString(it.weather)
        )
    }
}

fun getFavFromList(skiResortListDb: List<com.alexandre.skiresortmvp.data.db.model.SkiResort>, id: Int): Boolean {
    val mutableIterator = skiResortListDb.iterator()

    // iterator() extension is called here
    for (skiResort in mutableIterator) {
        if(id == skiResort.skiResortId)
            return skiResort.isFav
    }
    return false
}


fun toDbModel(skiResortList: List<com.alexandre.skiresortmvp.data.network.model.SkiResort>) :
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
        SkiResort(
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

fun getDrawableForString(weatherString: String) : Int? {
    when (weatherString) {
        "sunny" -> return R.drawable.ic_wb_sunny
        "cloudy" -> return R.drawable.ic_wb_cloudy
        "snow" -> return R.drawable.ic_ac_unit
        "rain" -> return R.drawable.ic_grain
    }
    return null
}