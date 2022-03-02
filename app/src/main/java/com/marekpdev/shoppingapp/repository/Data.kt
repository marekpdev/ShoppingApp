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
        availableColors = getColors(),
        availableSizes = getSizes(),
        images = getImages(),
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

    fun getMenu(): Pair<List<Category>, List<Product>> {
        val categories = mutableListOf<Category>()
        val products = mutableListOf<Product>()

        val root = Category(ROOT_CATEGORY_ID, NO_CATEGORY_ID, "ROOT")
        val womenCategory = Category(1000, ROOT_CATEGORY_ID, "Woman")
        val manCategory = Category(2000, ROOT_CATEGORY_ID, "Man")
        val accessoriesCategory = Category(3000, ROOT_CATEGORY_ID, "Accessories")

        val womanCatLabels = listOf(
            "Blouses","Shirts", "Trousers", "Jeans", "Shorts", "Skirts", "Dresses", "Jumpers"
        )

        val manCatLabels = listOf(
            "Jumpers", "Hoodies", "T-shirts", "Polo Shirts", "Suits"
        )

        val accessoriesCatLabels = listOf(
            "Bags", "Hats", "Gloves", "Jewellery", "Belts", "Sunglasses"
        )




        return categories to products
    }

    var currentPriceIndex = 0
    var prices = listOf(10.3, 15.0, 18.0, 22.0, 5.0, 3.5, 12.0)
    fun getPrice(): Double {
        return prices[currentPriceIndex++ % prices.size]
    }

    var currentColorIndex = 0
    val colors2 = mutableListOf(
        Color(1, "light sea green", "#17C3B2"),
        Color(2, "CG Blue", "#227C9D"),
        Color(3, "maximum yellow red", "#FFCB77"),
        Color(4, "winter blue", "#398AB9"),
        Color(5, "Retro black", "#19282F"),
        Color(6, "Night navy", "#21325E"),
    )
    fun getColors(): List<Color> {
        currentColorIndex++
        return when(currentColorIndex % colors2.size){
            1 -> listOf(colors2[0], colors2[1], colors2[2])
            2 -> listOf(colors2[2], colors2[3], colors2[4])
            3 -> listOf(colors2[4], colors2[5])
            4 -> listOf(colors2[1], colors2[5], colors2[2])
            5 -> listOf(colors2[2], colors2[3])
            else -> listOf(colors2[4])
        }
    }

    var currentSizeIndex = 0
    fun getSizes() = (1..9).map { Size(it, "0$it") }

    fun getImages() = listOf(
        "https://cdn.shopify.com/s/files/1/0932/1794/files/Artboard_24_370x230@2x.png?v=1605584742",
        "https://romans-cdn.rlab.net/images/extralarge/350f24c0-9476-465b-9c8a-5e0a70b6bf62.jpg",
        "https://cdn.foreverunique.com/products/uar212729_4205.jpg?h=480&w=",
        "https://media.sezane.com/image/upload/c_crop,fl_progressive:semi,h_0.95333333333333,q_auto:best,w_1,x_0,y_0.023333333333333/c_scale,w_598/whb5logixhjjmnqrurfp.jpg"
    )

}