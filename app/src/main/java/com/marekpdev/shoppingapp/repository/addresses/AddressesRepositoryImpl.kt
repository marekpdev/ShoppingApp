package com.marekpdev.shoppingapp.repository.addresses

import com.marekpdev.shoppingapp.models.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesRepositoryImpl @Inject constructor(): AddressesRepository{

    private val addressesPerUser = mapOf(
        1L to MutableStateFlow(getTestAddresses(1L))
    )

    override suspend fun getAddresses(userId: Long): StateFlow<List<Address>> {
        delay(1000L)
        return addressesPerUser[userId] ?: MutableStateFlow(emptyList())
    }

    override suspend fun getAddress(addressId: Long): Address? = withContext(Dispatchers.IO) {
        delay(1000L) // TODO just for testing
        val order = addressesPerUser.flatMap { it.value.value }.find { it.id == addressId }
        order
    }

    private fun getTestAddresses(userId: Long): List<Address> {
        // the actual time vary based on the current time
        return listOf(
            Address(1, userId, "Line1-1", "Line2", "Postcode", "City", "Country"),
            Address(2, userId, "Line1-2", "Line2", "Postcode", "City", "Country"),
            Address(3, userId, "Line1-3", "Line2", "Postcode", "City", "Country"),
            Address(4, userId, "Line1-4", "Line2", "Postcode", "City", "Country"),
            Address(5, userId, "Line1-5", "Line2", "Postcode", "City", "Country"),
        )
    }
}