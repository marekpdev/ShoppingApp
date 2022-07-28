package com.marekpdev.shoppingapp.repository.addresses

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.AddressCreator
import com.marekpdev.shoppingapp.models.toAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesRepositoryImpl @Inject constructor(): AddressesRepository{

    private val testAddressesIds = AtomicLong(1)

    private val addressesPerUser = mutableMapOf(
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

    override suspend fun addAddress(userId: Long, newAddressCreator: AddressCreator) {
        val newAddress = newAddressCreator.toAddress(testAddressesIds.getAndIncrement(), userId)
        val currentUserAddresses = addressesPerUser.getOrDefault(userId, MutableStateFlow(emptyList())).value
        val updatedUserAddresses = currentUserAddresses.toMutableList().apply { add(newAddress) }
        addressesPerUser[userId]?.value = updatedUserAddresses
    }

    override suspend fun updateAddress(addressToUpdate: Address, updatedAddressCreator: AddressCreator) {
        val updatedAddress = addressToUpdate.copy(
            line1 = updatedAddressCreator.line1,
            line2 = updatedAddressCreator.line2,
            postcode = updatedAddressCreator.postcode,
            city = updatedAddressCreator.city,
            country = updatedAddressCreator.country
        )

        val userId = addressToUpdate.userId
        val currentUserAddresses = addressesPerUser.getOrDefault(userId, MutableStateFlow(emptyList())).value
        val updatedUserAddresses = currentUserAddresses.toMutableList().map { address ->
            if (address == addressToUpdate) updatedAddress else address
        }
        addressesPerUser[userId]?.value = updatedUserAddresses
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