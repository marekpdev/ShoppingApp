package com.marekpdev.shoppingapp.repository

import com.marekpdev.shoppingapp.models.*
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.OrderProduct
import com.marekpdev.shoppingapp.models.order.OrderStatus
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import java.util.*
import kotlin.random.Random

/**
 * Created by Marek Pszczolka on 02/03/2022.
 */
object Data {

    fun getProduct(id: Long, categoryId: Int) = Product(
        id = id,
        name = names.getRandomItem(),
        description = descriptions.getRandomItem(),
        price = prices.getRandomItem(),
        oldPrice = null,
        currency = "$",
        availableColors = colors.getRandomItems(),
        availableSizes = sizes.getRandomItems(),
        images = images.getRandomItems(),
        categoryId = categoryId
    )

    fun getProducts(count: Int, categoryId: Int): List<Product>{
        return (1..count).map { getProduct(it.toLong(), categoryId) }
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
        getProducts(5, 1).toOrderProducts(),
        300.0,
        getAddress(),
        PaymentMethod("VISA **** 1234"),
        1000000L,
        OrderStatus.IN_PROGRESS
    )

    fun getOrders(count: Int): List<Order>{
        return (1..count).map { getOrder(it.toLong()) }
    }

    val prices = Randomizer(
        listOf(10.5, 15.0, 18.0, 22.0, 5.0, 3.5, 12.0)
    )

    val sizes = Randomizer(
        (1..9).map { Size(it, "0$it") }
    )

    val colors = Randomizer(
        listOf(
            Color(1, "light sea green", "#17C3B2"),
            Color(2, "CG Blue", "#227C9D"),
            Color(3, "maximum yellow red", "#FFCB77"),
            Color(4, "winter blue", "#398AB9"),
            Color(5, "Retro black", "#19282F"),
            Color(6, "Night navy", "#21325E"),
        )
    )

    val images = Randomizer(
        listOf(
            "https://oldnavy.gap.com/Asset_Archive/ONWeb/content/0028/280/679/assets/211225_60-M6165_W_DP_NewArrivals.jpg",
            "https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1620399389-best-amazon-dresses-for-women-ruffle-dress-1620399361.png?crop=0.786xw:0.985xh;0.101xw,0.0150xh&resize=480:*",
            "https://img.ltwebstatic.com/images3_pi/2021/05/08/16204416771a563dba0f844785d5e25f2dcb369cd3_thumbnail_600x.webp",
            "https://img.ltwebstatic.com/images3_pi/2020/10/17/160290647269304c85654dd69e3abf0f15f8351cdf_thumbnail_600x.webp",
            "https://img.ltwebstatic.com/images3_pi/2021/06/30/1625046216f42f94994c13312f8deb738e54d025d8_thumbnail_600x.webp",
            "https://cdn-img.prettylittlething.com/a/9/a/6/a9a6c9a4c66800a9e0f3e95389853804be76f986_cmv8179_1.jpg?imwidth=400",
            "https://cdn.shopify.com/s/files/1/0932/1794/files/Artboard_24_370x230@2x.png?v=1605584742",
            "https://romans-cdn.rlab.net/images/extralarge/350f24c0-9476-465b-9c8a-5e0a70b6bf62.jpg",
            "https://cdn.foreverunique.com/products/uar212729_4205.jpg?h=480&w=",
            "https://i.pinimg.com/originals/cb/f7/37/cbf737760fbda7b208217ebf411e284c.jpg",
            "https://www.bodenimages.com/productimages/productlarge/22wspr_t0892_khk.jpg",
            "https://asset1.cxnmarksandspencer.com/is/image/mands/SD_01_T41_5023W_DW_X_EC_0",
            "https://media.sezane.com/image/upload/c_crop,fl_progressive:semi,h_0.95333333333333,q_auto:best,w_1,x_0,y_0.023333333333333/c_scale,w_598/whb5logixhjjmnqrurfp.jpg"
        )
    )

    val descriptions = Randomizer(listOf(
        "Pinstripped cornflower blue cotton blouse takes you on a walk to the park or just down the hall.",
        "V neck\n" +
                "long fitted sleeves\n" +
                "\n" +
                "\n" +
                "Height of female model: 180 cm\n" +
                "\n" +
                "Model wears size S/36",
        "two side pockets\n" +
                "100% cotton",
        "round neck\n" +
                "dropped shoulders\n" +
                "long sleeves\n" +
                "ribbed neckline, hemline and cuffs",
        "ORGANIC COTTON\n" +
                "\n" +
                "It is obtained using plant protection products and fertilizers of natural origin. Genetically modified seeds are not used in its production. It is cultivated in a way that helps preserve soil fertility, protects biodiversity and follows the natural cycle of life. Organic fibres used in the production of Eco Aware collection models are certified by independent institutions in line with the Organic Content Standard (OCS) or the Global Organic Textile Standard (GOTS). We verify each certification and compile all documentation confirming that the fabric is compliant with our regulations.",
        "round neck\n" +
                "short sleeves\n" +
                "\n" +
                "\n" +
                "model’s height: 185 cm\n" +
                "\n" +
                "Model wears size M",


        ))

    val names = Randomizer(
        (1..100).map { "Product Name $it" }
    )

    fun getMenu(): Pair<List<Category>, List<Product>> {
        val categories = mutableListOf<Category>()
        val products = mutableListOf<Product>()

        val root = Category(ROOT_CATEGORY_ID, NO_CATEGORY_ID, "ROOT")
        categories.add(root)

        val cat = listOf(
            "Woman" to listOf("Blouses","Shirts", "Trousers", "Jeans", "Shorts", "Skirts", "Dresses", "Jumpers"),
            "Woman 2" to listOf("Jumpers", "Hoodies", "T-shirts", "Polo Shirts", "Suits"),
            "Accessories" to listOf("Bags", "Hats", "Gloves", "Jewellery", "Belts", "Sunglasses")
        )

        cat.forEach {
            val mainCategoryName = it.first
            val mainCategory = Category(getNextCategoryId(), ROOT_CATEGORY_ID, mainCategoryName)
            categories.add(mainCategory)
            val subcategoriesNames = it.second
            subcategoriesNames.forEach { subcategoryName ->
                val subcategory = Category(getNextCategoryId(), mainCategory.id, subcategoryName)
                categories.add(subcategory)
                val randomProductSize = Random(categoryId).nextInt(5, 20)
                (1..randomProductSize).forEach {
                    val product = getProduct(getNextProductId(), subcategory.id)
                    products.add(product)
                }
            }

        }

        return categories to products
    }

    var categoryId = 1
    fun getNextCategoryId() = categoryId++

    var productId = 1L
    fun getNextProductId() = productId++

}