package com.marekpdev.shoppingapp.repository.homebanners

import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.HomeBanner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
class HomeBannersRepositoryImpl @Inject constructor(): HomeBannersRepository {

    private val homeBanners = MutableStateFlow(
        listOf(
            HomeBanner(R.drawable.home_banner_1),
            HomeBanner(R.drawable.home_banner_2),
            HomeBanner(R.drawable.home_banner_3),
        )
    )

    override fun getHomeBanners(): StateFlow<List<HomeBanner>> {
        return homeBanners
    }
}