package com.alexandre.skiresortmvp

import com.alexandre.skiresortmvp.data.db.SkiResortDao
import com.alexandre.skiresortmvp.data.db.model.SkiResort
import com.alexandre.skiresortmvp.data.network.SkiResortListService
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortList
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListInteractor
import com.alexandre.skiresortmvp.ui.skiresortlist.SkiResortListPresenter
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SkiResortInteractorTest {
    private val skiResortDao = mockkClass(SkiResortDao::class)
    private val skiResortListService = MockSkiResortListService(createRetrofitDelegate())
    private val view = mockkClass(SkiResortList.View::class)
    private val presenter = spyk(SkiResortListPresenter(view))

    @Before
    fun prepareDao() {
        every {
            skiResortDao.getAllSkiResorts()
        } returns createExpectedDbData()

        every {
            skiResortDao.isFav(1)
        } returns true

        every {
            skiResortDao.insertAll(createExpectedDbData())
        } returns Unit

        every {
            view.displaySkiResortList(createExpectedResult())
        } returns Unit

        every {
            view.displaySkiResortList(createExpectedResultDb())
        } returns Unit
    }

    private fun createRetrofitDelegate(): BehaviorDelegate<SkiResortListService> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
            .build()

        val behavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior)
            .build()

        behavior.setDelay(0, TimeUnit.MILLISECONDS)

        return mockRetrofit.create(SkiResortListService::class.java)
    }

    @Test
    fun testLoadSkiResortList() {
        val interactor = SkiResortListInteractor(presenter,
            skiResortListService
            , skiResortDao,
            Executors.newSingleThreadExecutor())

        interactor.loadSkiResortList()

        skiResortListService.getSkiResorts()

        verify(exactly = 1) { presenter.responseSkiResortList(createExpectedResultDb()) }
        verify(exactly = 1) { presenter.responseSkiResortList(createExpectedResult()) }
    }


    private fun createExpectedResult() =
        listOf(com.alexandre.skiresortmvp.domain.SkiResort(
            1,
            "Val d'Isère",
            "France",
            "Alps",
            300,
            83,
            96,
            true,
            R.drawable.ic_wb_sunny))

    private fun createExpectedResultDb() =
        listOf(com.alexandre.skiresortmvp.domain.SkiResort(
            1,
            "Val d'Isère",
            "France",
            "Alps",
            300,
            83,
            96,
            true,
            null))

    private fun createExpectedDbData() =
        listOf(SkiResort(
            1,
            "Val d'Isère",
            "France",
            "Alps",
            300,
            83,
            96,
            true))
}