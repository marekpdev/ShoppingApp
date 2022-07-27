package com.marekpdev.shoppingapp.repository.paymentmethods

import com.marekpdev.shoppingapp.models.order.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
interface PaymentMethodsRepository {

    suspend fun getPaymentMethods(userId: Long): StateFlow<List<PaymentMethod>>

    suspend fun getPaymentMethod(paymentMethodId: Long): PaymentMethod?
}