package com.projectx.fisioapp.app.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.CatalogDetailFragment
import com.projectx.fisioapp.app.fragment.CatalogItemListener
import com.projectx.fisioapp.app.utils.EXTRA_CATALOG_TYPE
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.catalog.DeleteCatalogIntImpl
import com.projectx.fisioapp.domain.interactor.catalog.DeleteCatalogInteractor
import com.projectx.fisioapp.domain.model.Catalog
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * An activity representing a single Service detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [CatalogListActivity].
 */
class CatalogDetailActivity : CatalogParentActivity(), CatalogItemListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_detail)

        val arguments = Bundle()

        if(intent.getSerializableExtra(CatalogDetailFragment.ARG_ITEM) != null){
            arguments.putSerializable(CatalogDetailFragment.ARG_ITEM,
                    intent.getSerializableExtra(CatalogDetailFragment.ARG_ITEM))
        }

        arguments.putSerializable(EXTRA_CATALOG_TYPE,
                intent.getSerializableExtra(EXTRA_CATALOG_TYPE))

        val fragment = CatalogDetailFragment()
        fragment.arguments = arguments
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_list_detail_fragment, fragment)
                .commit()



        /*
        //setSupportActionBar(detail_toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString(CatalogDetailFragment.ARG_ITEM,
                    intent.getStringExtra(CatalogDetailFragment.ARG_ITEM))
            val fragment = CatalogDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_list_detail_fragment, fragment)
                    .commit()
        }*/
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, CatalogListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }


    override fun onSavePressed(view: View, item: Catalog) {
       // TODO
    }

    override fun onDeletePressed(view: View, id: String) {
        async(UI) {

            val deleteItem: DeleteCatalogInteractor = DeleteCatalogIntImpl(view.context)
            try {
                deleteItem.execute(token,
                        id,
                        success = object : SuccessCompletion<String> {
                            override fun successCompletion(e: String) {
                                ToastIt(view.context, "$e")

                                // TODO improve the back, to refresh the catalog list with the last data
                                finish()
                            }
                        }, error = object : ErrorCompletion {
                            override fun errorCompletion(errorMessage: String) {
                                ToastIt(view.context, "$errorMessage")
                            }
                        })
            } catch (e: Exception) {
                ToastIt(view.context, "Error: " + e.localizedMessage )
            }
        }
    }
}
