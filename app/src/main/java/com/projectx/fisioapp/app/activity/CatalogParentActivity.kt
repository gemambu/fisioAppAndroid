package com.projectx.fisioapp.app.activity

import android.os.Bundle
import android.util.Log
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.router.Router
import kotlinx.android.synthetic.main.activity_catalog.*

open class CatalogParentActivity : ParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        btnServices.setOnClickListener {
            Log.d(CatalogParentActivity::class.java.canonicalName, "Clicked on Services")
            Router().navigateFromParentActivityToServicesActivity(this)
        }

        btnProducts.setOnClickListener {
            Log.d(CatalogParentActivity::class.java.canonicalName, "Clicked on Products")
            Router().navigateFromParentActivityToProductsActivity(this)
        }

    }

}