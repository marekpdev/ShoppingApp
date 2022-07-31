package com.marekpdev.shoppingapp.repository.paymentmethods

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.models.payments.PaymentCardCreator
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
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
class PaymentMethodsRepositoryImpl @Inject constructor(): PaymentMethodsRepository{

    private val testPaymentMethodsIds = AtomicLong(1)

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
            PaymentCard(testPaymentMethodsIds.getAndIncrement(), userId, "XXXXX1", "VISA", "4242"),
            PaymentCard(testPaymentMethodsIds.getAndIncrement(), userId, "XXXXX2", "VISA", "3131")
        )
    }

    override suspend fun addPaymentCard(userId: Long, newPaymentMethodCreator: PaymentCardCreator) {
        val newPaymentCard = PaymentCard(
            testPaymentMethodsIds.getAndIncrement(),
            userId,
            newPaymentMethodCreator.cardNumber.hashCode().toString(),
            "VISA",
            newPaymentMethodCreator.cardNumber.takeLast(4)
        )
        val currentUserPaymentMethods = paymentMethodsPerUser.getOrDefault(userId, MutableStateFlow(emptyList())).value
        val updatedUserPaymentMethods = currentUserPaymentMethods.toMutableList().apply { add(newPaymentCard) }
        paymentMethodsPerUser[userId]?.value = updatedUserPaymentMethods
    }

    override suspend fun updatePaymentCard(
        paymentCardToUpdate: PaymentCard,
        updatedPaymentMethodCreator: PaymentCardCreator
    ) {
        val updatedPaymentCard = paymentCardToUpdate.copy(
            hash = updatedPaymentMethodCreator.cardNumber.hashCode().toString(),
            provider = "VISA",
            last4Digits = updatedPaymentMethodCreator.cardNumber.takeLast(4)
        )
        val userId = paymentCardToUpdate.userId
        val currentUserPaymentMethods = paymentMethodsPerUser.getOrDefault(userId, MutableStateFlow(emptyList())).value
        val updatedUserPaymentMethods = currentUserPaymentMethods.toMutableList().map { paymentMethod ->
            if (paymentMethod == paymentCardToUpdate) updatedPaymentCard else paymentMethod
        }
        paymentMethodsPerUser[userId]?.value = updatedUserPaymentMethods
    }
}