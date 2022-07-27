package com.marekpdev.shoppingapp.repository.addresses

import com.marekpdev.shoppingapp.models.Address
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
interface AddressesRepository {

    suspend fun getAddresses(userId: Long): StateFlow<List<Address>>

    suspend fun getAddress(addressId: Long): Address?
}