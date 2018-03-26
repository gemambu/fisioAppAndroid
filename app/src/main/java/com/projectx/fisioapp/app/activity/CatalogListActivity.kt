package com.projectx.fisioapp.app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.activity.dummy.DummyContent
import com.projectx.fisioapp.app.fragment.CatalogDetailFragment
import com.projectx.fisioapp.app.settingsmanager.SettingsManager
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.catalog.GetCatalogIntImpl
import com.projectx.fisioapp.domain.interactor.catalog.GetCatalogInteractor
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
class CatalogListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false
    private var list: Catalogs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        catalog_list_add_element.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (service_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        setupRecyclerView(service_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        // TODO Get in this point the information related with products/services, and send it to the adapter
        getCatalogItems("service", this)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, list, mTwoPane)
        // set two columns with the elements
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getCatalogItems(type: String, context: Context) {
        async(UI) {

            val getAllCatalogItems: GetCatalogInteractor = GetCatalogIntImpl(context)
            try {
                val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7Il9pZCI6IjVhYWFkOWQ2NzE3MDgwNThkODZiOGUxMCIsIm5hbWUiOiJnZW1hIiwicGFzc3dvcmQiOiIzYzhkZGUxNmJkZDMzOTMwODQxODhkODEwMzE0ZGY3NDhlYTY5NTEwMzk2Y2MzZDk5ZTE2Yzc2ODhjZDQ1MmQ0IiwiZW1haWwiOiJleGFtcGxlNkBray5jb20iLCJpc1Byb2Zlc3Npb25hbCI6dHJ1ZSwiZ2VuZGVyIjoiZmVtYWxlIiwibGFzdE5hbWUiOiJtYiIsImFkZHJlc3MiOiJteSBhZGRyZXNzIiwiX192IjowLCJkZWxldGVkIjpmYWxzZX0sImlhdCI6MTUyMTk4MTE0OCwiZXhwIjoxNTIyMTUzOTQ4fQ.wpXtybj08J7IrYzh2IoY5bs8bBFKyMXi34MksAieLnU"
                getAllCatalogItems.execute(token,
                        type,
                        success = object : SuccessCompletion<Catalogs> {
                            override fun successCompletion(e: Catalogs) {
                                ToastIt(baseContext, "Items downloaded: $e.size")
                                list = e
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
                val item = v.tag as DummyContent.DummyItem
                if (mTwoPane) {
                    val fragment = CatalogDetailFragment().apply {
                        arguments = Bundle()
                        arguments.putString(CatalogDetailFragment.ARG_ITEM_ID, item.name)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.service_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, CatalogDetailActivity::class.java).apply {
                        putExtra(CatalogDetailFragment.ARG_ITEM_ID, item.name)
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
