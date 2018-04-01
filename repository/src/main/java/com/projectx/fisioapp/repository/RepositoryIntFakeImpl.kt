package com.projectx.fisioapp.repository

import android.content.Context
//import com.projectx.fisioapp.repository.cache.CacheIntFakeImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import java.lang.ref.WeakReference


<<<<<<< HEAD
/*class RepositoryIntFakeImpl(val context: Context): RepositoryInteractor {
    override fun deleteService(token: String, id: String, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit) {
=======
class RepositoryIntFakeImpl(val context: Context): RepositoryInteractor {
>>>>>>> services_and_products_gema



    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntFakeImpl(weakContext.get() !!)

    /******** users ********/
    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeAuthenticateUser()
            success(token)
        } else {
            error("Error while authenticating user")
        }
    }

    override fun registerUser(name: String, email: String, password: String, success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            var ok = true
            success(ok)
        } else {
            error("Error while getting registering user")
        }
    }

    fun createFakeAuthenticateUser(): String {
        val token = "AuthenticateUser12345"
        return token
    }

    /******** catalog (products and services) ********/
    override fun countCatalogItems(): Int = 10

    override fun getCatalogItems(forceUpdate: Boolean, token: String, type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.getAllCatalogItems(type,
                success = {
                    // if there are entities in the cache, return them
                    success(it)
                }, error = {
            // if no catalog in cache --> network
            populateCache(type, success, error)
        })

    }

    private fun populateCache(type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request
    }

    override fun deleteAllCatalogItems(success: () -> Unit, error: (errorMessage: String) -> Unit) = cache.deleteAllCatalogItems(success, error)

    override fun saveCatalogData(token: String, item: CatalogData, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCatalogData(token: String, id: String, type: String, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit) {

<<<<<<< HEAD
}*/
=======
    }
}
>>>>>>> services_and_products_gema
