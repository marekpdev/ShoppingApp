package com.marekpdev.shoppingapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@Parcelize
@Entity(tableName = "products")
data class Product (
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val oldPrice: Double?,
    val currency: String,
    val availableColors: List<Color>,
    val availableSizes: List<Size>,
    val images: List<String>,
    val categoryId: Int
) : Parcelable
