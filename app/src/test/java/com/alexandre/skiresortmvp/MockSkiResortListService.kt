package com.alexandre.skiresortmvp

import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.data.network.model.SkiResort
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate

class MockSkiResortListService(private val delegate: BehaviorDelegate<SkiResortListService>) : SkiResortListService {
    override fun getSkiResorts(): Call<List<SkiResort>> {
        val response = listOf(
            SkiResort(1,
                "Val d'Isère",
                "France",
                "Alps",
                300,
                83,
                96,
                "sunny")
        )
        return delegate.returningResponse(response).getSkiResorts()
    }
}