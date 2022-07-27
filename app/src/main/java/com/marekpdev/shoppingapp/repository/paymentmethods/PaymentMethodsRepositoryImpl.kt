package com.marekpdev.shoppingapp.repository.paymentmethods

import com.marekpdev.shoppingapp.models.order.PaymentMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentMethodsRepositoryImpl @Inject constructor(): PaymentMethodsRepository{

    private val paymentMethodsPerUser = mapOf(
        1L to MutableStateFlow(getTestPaymentMethods(1L))
    )

    override suspend fun getPaymentMethods(userId: Long): StateFlow<List<PaymentMethod>> {
        delay(1000L)
        return paymentMethodsPerUser[userId] ?: MutableStateFlow(emptyList())
    }

    override suspend fun getPaymentMethod(paymentMethodId: Long): PaymentMethod? = withContext(Dispatchers.IO) {
        delay(1000L) // TODO just for testing
        val paymentMethod = paymentMethodsPerUser.flatMap { it.value.value }.find { it.id == paymentMethodId }
        paymentMethod
    }

    private fun getTestPaymentMethods(userId: Long): List<PaymentMethod> {
        // the actual time vary based on the current time
        return listOf(
            PaymentMethod(1, userId, "Visa 1"),
            PaymentMethod(2, userId, "Visa 2"),
            PaymentMethod(3, userId, "Visa 3"),
            PaymentMethod(4, userId, "Visa 4"),
        )
    }
}