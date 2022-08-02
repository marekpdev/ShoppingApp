package com.marekpdev.shoppingapp.api.fake

import com.marekpdev.shoppingapp.api.AddressesApi
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.AddressCreator
import com.marekpdev.shoppingapp.models.toAddress
import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Marek Pszczolka on 02/08/2022.
 */
class FakeAddressesApi: AddressesApi {

    private val testAddressesIds = AtomicLong(1)

    private val addressesPerUser = mutableMapOf(
        1L to getTestAddresses(1L)
    )

    override suspend fun getAddresses(userId: Long): List<Address> {
        delay(1000L)
        return addressesPerUser[userId] ?: emptyList()
    }

    override suspend fun getAddress(addressId: Long): Address? {
        delay(1000L) // TODO just for testing
        return addressesPerUser.flatMap { it.value }.find { it.id == addressId }
    }

    override suspend fun addAddress(userId: Long, newAddressCreator: AddressCreator) {
        val newAddress = newAddressCreator.toAddress(testAddressesIds.getAndIncrement(), userId)
        addressesPerUser[userId] = addressesPerUser.getOrDefault(userId, mutableListOf()).toMutableList().apply { add(newAddress) }
    }

    override suspend fun updateAddress(
        addressToUpdate: Address,
        updatedAddressCreator: AddressCreator
    ) {
        TODO("Not yet implemented")
    }

    private fun getTestAddresses(userId: Long): List<Address> {
        // the actual time vary based on the current time
        return listOf(
            Address(testAddressesIds.getAndIncrement(), userId, "Line1-1", "Line2", "Postcode", "City", "Country"),
            Address(testAddressesIds.getAndIncrement(), userId, "Line1-2", "Line2", "Postcode", "City", "Country"),
            Address(testAddressesIds.getAndIncrement(), userId, "Line1-3", "Line2", "Postcode", "City", "Country"),
            Address(testAddressesIds.getAndIncrement(), userId, "Line1-4", "Line2", "Postcode", "City", "Country"),
            Address(testAddressesIds.getAndIncrement(), userId, "Line1-5", "Line2", "Postcode", "City", "Country"),
        )
    }
}