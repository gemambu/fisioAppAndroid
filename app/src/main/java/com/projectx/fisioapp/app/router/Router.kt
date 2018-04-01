package com.projectx.fisioapp.app.router

import android.content.Intent
import com.projectx.fisioapp.app.activity.*
import com.projectx.fisioapp.app.utils.CatalogType
import com.projectx.fisioapp.app.utils.EXTRA_CATALOG_TYPE
import com.projectx.fisioapp.domain.model.Appointment


class Router {

    fun navigateFromAppointmentsActivitytoLoginActivity (main: AppointmentsActivity) {
        main.startActivity(Intent(main, LoginActivity::class.java))
    }

    fun navigateFromLoginActivitytoAppointmentsActivity (main: LoginActivity) {
        main.startActivity(Intent(main, AppointmentsActivity::class.java))
    }

    fun navigateFromLoginActivitytoBlankActivity (main: LoginActivity) {
        main.startActivity(Intent(main, BlankActivity::class.java))
    }

    fun navigateFromCalendarActivityToAppointmentDetailActivity(main: CalendarActivity, appointment: Appointment){
        val intent = AppointmentDetailActivity.newInstance(main.baseContext, appointment)
        main.startActivity(intent)
        //main.startActivity(Intent(main.baseContext, AppointmentDetailActivity::class.java))
    }

    fun navigateFromCalendarActivityToLoginActivity(main: CalendarActivity) {
        main.startActivity(Intent(main, LoginActivity::class.java))
    }

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
        mainActivity.startActivity(intent)
    }
}
