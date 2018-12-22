package com.alexandre.skiresortmvp.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.alexandre.skiresortmvp.data.db.model.SkiResort

/**
 * DAO for ski resorts
 */
@Dao
interface SkiResortDao{

    //Add a list of ski resorts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(skiResortList: List<SkiResort>)

    //Get all the ski resorts
    @Query("SELECT skiResortId, name, country, mountainRange, slopeKm, lifts, slopes, isFav FROM ski_resorts")
    fun getAllSkiResorts(): List<SkiResort>

    @Query("UPDATE ski_resorts SET isFav = :isFav WHERE skiResortId = :skiResortId")
    fun updateFav(skiResortId : Int, isFav : Boolean)

    @Query("SELECT isFav FROM ski_resorts WHERE skiResortId = :skiResortId")
    fun isFav(skiResortId : Int): Boolean
}