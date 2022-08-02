package com.marekpdev.shoppingapp.repository.addresses

import com.marekpdev.shoppingapp.api.AddressesApi
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
class AddressesRepositoryImpl @Inject constructor(private val addressesApi: AddressesApi): AddressesRepository{

    private val addressesPerUser = mutableMapOf<Long, MutableStateFlow<List<Address>>>()

    override suspend fun getAddresses(userId: Long): StateFlow<List<Address>> {
        return when(addressesPerUser.containsKey(userId)){
            true -> addressesPerUser[userId]?: MutableStateFlow(emptyList())
            else -> MutableStateFlow<List<Address>>(emptyList()).also { addressesPerUser[userId] = it}
        }
    }

    override suspend fun getAddress(addressId: Long): Address? = withContext(Dispatchers.IO) {
        return@withContext addressesApi.getAddress(addressId)
    }

    override suspend fun addAddress(userId: Long, newAddressCreator: AddressCreator) {
        addressesApi.addAddress(userId, newAddressCreator)

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


}