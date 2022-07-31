package com.marekpdev.shoppingapp.repository.paymentmethods

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.AddressCreator
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.models.payments.PaymentCardCreator
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
interface PaymentMethodsRepository {

    suspend fun getPaymentMethods(userId: Long): StateFlow<List<PaymentMethod>>

    suspend fun getPaymentMethod(paymentMethodId: Long): PaymentMethod?

    suspend fun addPaymentCard(userId: Long, newPaymentMethodCreator: PaymentCardCreator)

    suspend fun updatePaymentCard(paymentCardToUpdate: PaymentCard, updatedPaymentMethodCreator: PaymentCardCreator)
}