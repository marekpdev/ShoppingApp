package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Address(
    val id: Long,
    val userId: Long,
    val line1: String,
    val line2: String,
    val postcode: String,
    val city: String,
    val country: String,
)

data class AddressCreator(
    val line1: String,
    val line2: String,
    val postcode: String,
    val city: String,
    val country: String
)

fun AddressCreator.toAddress(id: Long, userId: Long) = Address(
    id = id,
    userId = userId,
    line1 = line1,
    line2 = line2,
    postcode = postcode,
    city = city,
    country = country
)