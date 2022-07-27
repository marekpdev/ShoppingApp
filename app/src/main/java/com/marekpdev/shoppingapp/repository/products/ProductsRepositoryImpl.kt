package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
class ProductsRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    private val allMenu = MutableStateFlow(Data.getMenu())

    init {

    }

    override suspend fun getProduct(id: Long): Product? = withContext(Dispatchers.IO) {
        delay(1000L) // TODO just for testing
        val product = allMenu.value.products.find { it.id == id }
        product
    }

    override suspend fun toggleFavourite(product: Product): Product {
        val indexOf = allMenu.value.products.indexOf(product)

        if (indexOf >= 0) {
            val newFavourite = !product.isFavoured
            val newProduct = product.copy(isFavoured = newFavourite)
            val newProductsList = allMenu.value.products.toMutableList()
            newProductsList[indexOf] = newProduct
            val newMenu = allMenu.value.copy(
                products = newProductsList
            )
            allMenu.emit(newMenu)
            return newProduct
        }

        return product
    }

    override fun getAllMenu(): StateFlow<Menu> {
        return allMenu
    }

    override suspend fun getMenuForCategory(categoryId: Int): Menu {
        val categories = allMenu.value.categories.filter { categoryId ==  it.id }
        val products = allMenu.value.products.filter { categoryId in it.parentCategoryIds }
        return Menu(categories, products)
    }
}