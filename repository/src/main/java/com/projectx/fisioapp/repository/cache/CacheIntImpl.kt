package com.projectx.fisioapp.repository.cache

import android.content.Context
import com.projectx.fisioapp.repository.db.DBHelper
import com.projectx.fisioapp.repository.db.buildHelper
import com.projectx.fisioapp.repository.db.dao.CatalogDAO
import com.projectx.fisioapp.repository.BuildConfig
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference


class CacheIntImpl(context: Context): CacheInteractor {

    private val context = WeakReference<Context>(context)
    private val dbHelper = cacheDBHelper()


    private fun cacheDBHelper(): DBHelper {
        return buildHelper(context.get()!!, BuildConfig.FISIOAPP_CACHE_DB_NAME, 1)
    }


    /******** users ********/


    /******** catalog (products and services) ********/
    override fun countCatalogItems(): Int {

        val total = CatalogDAO(dbHelper).count()
        dbHelper.close()
        return total

    }

    override fun getAllCatalogItems(type: String, success: (catalogList: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {

        Thread(Runnable {
            val entityList = CatalogDAO(dbHelper).query(type)

            if (entityList.isNotEmpty()) {
                success(entityList)
            } else {
                error("Error getting $type list")
            }
            dbHelper.close()
        }).run()

    }

    override fun saveCatalogItems(type: String, catalogList: List<CatalogData>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                catalogList.forEach { CatalogDAO(dbHelper).insertOrUpdate(it, type) }

                DispatchOnMainThread(Runnable {
                    dbHelper.close()
                    success()
                })
            } catch (ex: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting items: " + ex.message.toString())
                    dbHelper.close()
                })
            }
        }).run()
    }
    override fun insertCatalogItem(item: CatalogData, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                val successInsert = CatalogDAO(dbHelper).insert(item, item.type.name )

                DispatchOnMainThread(Runnable {

                    if(successInsert > 0){
                        dbHelper.close()
                        success()
                    } else {
                        error("Error inserting")
                    }
                    dbHelper.close()
                })
            } catch (ex: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting item: " + ex.message.toString())
                    dbHelper.close()
                })
            }
        }).run()
    }

    override fun updateCatalogItem(item: CatalogData, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                val successUpdate = CatalogDAO(dbHelper).update(item.databaseId, item)

                DispatchOnMainThread(Runnable {

                    if(successUpdate.length > 0){
                        dbHelper.close()
                        success()
                    } else {
                        error("Error updating")
                    }
                    dbHelper.close()
                })
            } catch (ex: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error updating item: " + ex.message.toString())
                    dbHelper.close()
                })
            }
        }).run()
    }

    override fun deleteCatalogItem(id: String, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            val successDeleting = CatalogDAO(dbHelper).delete(id)

            DispatchOnMainThread(Runnable {
                if (successDeleting.length > 0) {
                    success()
                } else {
                    error("Error deleting")
                }
                dbHelper.close()
            })

        }).run()
    }

    override fun deleteAllCatalogItems(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            val successDeleting = CatalogDAO(dbHelper).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
                dbHelper.close()
            })

        }).run()

    }

}