package com.marekpdev.shoppingapp.repository

import com.marekpdev.shoppingapp.models.*
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.OrderProduct
import com.marekpdev.shoppingapp.models.order.OrderStatus
import com.marekpdev.shoppingapp.models.order.PaymentMethod

/**
 * Created by Marek Pszczolka on 02/03/2022.
 */
object Data {

    fun getProduct(id: Long) = Product(
        id = id,
        name = "Product $id",
        description = "Pinstripped cornflower blue cotton blouse takes you on a walk to the park or just down the hall.",
        price = id * 1.11,
        oldPrice = id * 1.44,
        currency = "$",
        availableColors = listOf(
            Color(1, "light sea green", "#17C3B2"),
            Color(2, "CG Blue", "#227C9D"),
            Color(3, "maximum yellow red", "#FFCB77")
        ),
        availableSizes = (1..9).map { Size(it, "0$it") },
        images = listOf(
            "https://cdn.shopify.com/s/files/1/0932/1794/files/Artboard_24_370x230@2x.png?v=1605584742",
            "https://romans-cdn.rlab.net/images/extralarge/350f24c0-9476-465b-9c8a-5e0a70b6bf62.jpg",
            "https://cdn.foreverunique.com/products/uar212729_4205.jpg?h=480&w=",
            "https://media.sezane.com/image/upload/c_crop,fl_progressive:semi,h_0.95333333333333,q_auto:best,w_1,x_0,y_0.023333333333333/c_scale,w_598/whb5logixhjjmnqrurfp.jpg"
        ),
        categoryId = 1
    )

    fun getProducts(count: Int): List<Product>{
        return (1..count).map { getProduct(it.toLong()) }
    }

    fun List<Product>.toOrderProducts(): List<OrderProduct> = map { product ->
        OrderProduct(
            id = 1L * product.id,
            productId = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            currency = product.currency,
            images = product.images,
            selectedSize = product.availableSizes.firstOrNull(),
            selectedColor = product.availableColors.firstOrNull(),
        )
    }

    fun getAddress() = Address(
        "Street1",
        "Street2",
        "EH1111",
        "Edin",
        "UK"
    )

    fun getOrder(id: Long) = Order(
        id,
        getProducts(5).toOrderProducts(),
        300.0,
        getAddress(),
        PaymentMethod("VISA **** 1234"),
        1000000L,
        OrderStatus.IN_PROGRESS
    )

    fun getOrders(count: Int): List<Order>{
        return (1..count).map { getOrder(it.toLong()) }
    }

    fun getUser() = User("myemail@gmail.com", "MyName", "MySurname")

}