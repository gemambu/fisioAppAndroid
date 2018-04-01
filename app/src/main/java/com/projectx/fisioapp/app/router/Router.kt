package com.projectx.fisioapp.app.router

import android.content.Intent
import com.projectx.fisioapp.app.activity.*
import com.projectx.fisioapp.app.fragment.CatalogDetailFragment
import com.projectx.fisioapp.app.utils.CatalogType
import com.projectx.fisioapp.app.utils.EXTRA_CATALOG_TYPE
import com.projectx.fisioapp.app.utils.RQ_OPERATION
import com.projectx.fisioapp.domain.model.Catalog


class Router {

    fun navigateFromCatalogListActivitytoLoginActivity(main: CatalogListActivity) {
        main.startActivity(Intent(main, LoginActivity::class.java))
    }

    fun navigateFromParentActivityToServicesActivity(mainActivity: CatalogParentActivity) {
        navigateToDetailActivity(mainActivity, CatalogType.SERVICE)
    }

    fun navigateFromParentActivityToProductsActivity(mainActivity: CatalogParentActivity) {
        navigateToDetailActivity(mainActivity, CatalogType.PRODUCT)
    }

    private fun navigateToDetailActivity(mainActivity: CatalogParentActivity, type: CatalogType) {
        val intent = Intent(mainActivity, CatalogListActivity::class.java)
        intent.putExtra(EXTRA_CATALOG_TYPE, type)
        mainActivity.startActivityForResult(intent, RQ_OPERATION)
    }

    fun navigateFromParentActivityToDetailCatalogActivity(item: Catalog, mainActivity: CatalogListActivity, type: CatalogType) {
        val intent = Intent(mainActivity, CatalogDetailActivity::class.java)
        intent.putExtra(EXTRA_CATALOG_TYPE, type)
        intent.putExtra(CatalogDetailFragment.ARG_ITEM, item)
        mainActivity.startActivityForResult(intent, RQ_OPERATION)
    }

    fun navigateFromParentActivityToNewCatalogActivity(mainActivity: CatalogListActivity, type: CatalogType) {
        val intent = Intent(mainActivity, CatalogDetailActivity::class.java)
        intent.putExtra(EXTRA_CATALOG_TYPE, type)
        mainActivity.startActivityForResult(intent, RQ_OPERATION)
    }
}
