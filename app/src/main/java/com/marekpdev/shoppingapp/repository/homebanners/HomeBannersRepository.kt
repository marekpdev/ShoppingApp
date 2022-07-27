package com.marekpdev.shoppingapp.repository.homebanners

import com.marekpdev.shoppingapp.models.HomeBanner
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface HomeBannersRepository {

    fun getHomeBanners(): StateFlow<List<HomeBanner>>

}