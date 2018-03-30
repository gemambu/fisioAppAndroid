package com.projectx.fisioapp.repository

import android.content.Context
import com.projectx.fisioapp.repository.cache.CacheIntImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.authenticateuser.AuthenticateUserInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.products.get.GetProductsIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.products.get.GetProductsInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.registeruser.RegisterUserIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.registeruser.RegisterUserInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.delete.DeleteServiceIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.delete.DeleteServiceInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.get.GetServicesIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.get.GetServicesInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.insert.InsertServiceIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.insert.InsertServiceInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.update.UpdateServiceIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.services.update.UpdateServiceInteractor
import retrofit2.Response.success
import java.lang.ref.WeakReference


class RepositoryIntImpl(val context: Context) : RepositoryInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntImpl(weakContext.get()!!)
    private val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntImpl()
    private val registerUser: RegisterUserInteractor = RegisterUserIntImpl()

    private val getAllServices: GetServicesInteractor = GetServicesIntImpl()
    private val insertService: InsertServiceInteractor = InsertServiceIntImpl()
    private val deleteService: DeleteServiceInteractor = DeleteServiceIntImpl()
    private val updateService: UpdateServiceInteractor = UpdateServiceIntImpl()

    private val getAllProducts: GetProductsInteractor = GetProductsIntImpl()

    /******** users ********/
    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {

        // perform network request
        authenticateUser.execute(email, password,
                success = {
                    success(it)
                }, error = {
                    error(it)
                }
        )

    }

    override fun registerUser(name: String, email: String, password: String, success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {

        // perform network request
        registerUser.execute(name, email, password,
                success = {
                    success(it)
                }, error = {
                    error(it)
                }
        )

    }

    /******** catalog (products and services) ********/
    override fun countCatalogItems(): Int {
        return cache.countCatalogItems()
    }

    override fun getAllCatalogItems(token: String, type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.getAllCatalogItems(type,
                success = {
                    // if there are entities in the cache, return them
                    success(it)
                }, error = {
                    // if no catalog in cache --> network
                    populateCache(token, type, success, error)
                }
        )
    }

    private fun populateCache(token: String, type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {

        when(type){
            "SERVICE" -> {
                // perform network request
                getAllServices.execute(token,
                        success = {
                            saveCatalog(it, type)
                            success(it)

                        }, error = {
                            error(it)
                        }
                )
            }
            "PRODUCT" -> {
                // perform network request
                getAllProducts.execute(token,
                        success = {
                            saveCatalog(it, type)
                            success(it)
                        }, error = {
                            error(it)
                        }
                )
            }
        }


    }

    private fun saveCatalog(list: List<CatalogData>, type: String) {
        cache.saveAllCatalogItems(type, list, success = {
            success(list)
        }, error = {
            error("Something happened on the way to heaven!")
        })
    }


    override fun deleteAllCatalogItems(success: () -> Unit, error: (errorMessage: String) -> Unit) = cache.deleteAllCatalogItems(success, error)

    override fun insertService(token: String, item: CatalogData, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit) {

        if (item.databaseId != null && item.databaseId != ""){
            //update option
            updateService.execute(token,
                    item,
                    success = {
                        updateCatalogInCache(item)
                        success(it)

                    }, error = {
                error(it)
            })
        } else {
            // insert option
            insertService.execute(token,
                    item,
                    success = {
                        insertCatalogInCache(item)
                        success(it)

                    }, error = {
                error(it)
            })
        }


    }

    private fun insertCatalogInCache(item: CatalogData) {
        cache.insertCatalogItem(item,
                success = {
                    success("Service ${item.name} inserted successfully")
                }, error = {
                    // if no catalog in cache --> network
                    error("Error inserting service: ${item.name}")
                }
                )
    }

    private fun updateCatalogInCache(item: CatalogData) {
        cache.updateCatalogItem(item,
                success = {
                    success("Service ${item.name} updated successfully")
                }, error = {
                    // if no catalog in cache --> network
                    error("Error updating service: ${item.name}")
                }
                )
    }



    override fun deleteService(token: String, id: String, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit){
        deleteService.execute(token, id,
                success = {
                    deleteServiceFromCache(id)
                    success(it)

                }, error = {
                    error(it)
                })
    }

    private fun deleteServiceFromCache(id: String) {
        cache.deleteService(id,
                success = {
                    success("Service $id removed successfully")
                }, error = {
                    // if no catalog in cache --> network
                    error("Error deleting service: $id")
                })
    }

}
