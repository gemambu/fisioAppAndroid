package com.projectx.fisioapp.app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.CatalogDetailFragment
import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.utils.CatalogType
import com.projectx.fisioapp.app.utils.EXTRA_CATALOG_TYPE
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.catalog.GetCatalogIntImpl
import com.projectx.fisioapp.domain.interactor.catalog.GetCatalogInteractor
import com.projectx.fisioapp.domain.model.Catalog
import com.projectx.fisioapp.domain.model.Catalogs
import kotlinx.android.synthetic.main.activity_catalog_list.*
import kotlinx.android.synthetic.main.catalog_list.*
import kotlinx.android.synthetic.main.catalog_list_content.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [CatalogDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class CatalogListActivity : ParentActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false
    private var list: Catalogs? = null
    private lateinit var type: CatalogType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_list)

        if (!checkToken()) {
            Router().navigateFromCatalogListActivitytoLoginActivity(this)
        } else {

            type = intent.getSerializableExtra(EXTRA_CATALOG_TYPE) as CatalogType

            setSupportActionBar(toolbar)
            toolbar.title = title

            catalog_list_add_element.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }

            if (catalog_detail_container != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true
            }

            getCatalogList(this)
            setupRecyclerView(catalog_list)
        }


    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, list, mTwoPane)
        // set two columns with the elements
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getCatalogList(context: Context) {
        async(UI) {

            val getAllCatalogItems: GetCatalogInteractor = GetCatalogIntImpl(context)
            try {
                getAllCatalogItems.execute(token,
                        type.name,
                        success = object : SuccessCompletion<Catalogs> {
                            override fun successCompletion(e: Catalogs) {
                                list = e
                                setupRecyclerView(catalog_list)
                            }
                        }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        ToastIt(baseContext, "$errorMessage")
                    }
                })
            } catch (e: Exception) {
                ToastIt(context, "Error: " + e.localizedMessage )
            }
        }
    }

    class SimpleItemRecyclerViewAdapter(private val mParentActivity: CatalogListActivity,
                                        private val mValues: Catalogs?,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                val item = v.tag as Catalog
                if (mTwoPane) {
                    val fragment = CatalogDetailFragment().apply {
                        arguments = Bundle()
                        arguments.putSerializable(CatalogDetailFragment.ARG_ITEM, item)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.catalog_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, CatalogDetailActivity::class.java).apply {
                        putExtra(CatalogDetailFragment.ARG_ITEM, item)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.catalog_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues!![position]
            holder.mIdView.text = item.name
            holder.mContentView.text = item.price.toString() + " €"

            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }

        override fun getItemCount(): Int {

            if (mValues !== null ) return mValues.count()

            return 0
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.catalog_list_element_name
            val mContentView: TextView = mView.catalog_list_element_price
        }
    }

}
