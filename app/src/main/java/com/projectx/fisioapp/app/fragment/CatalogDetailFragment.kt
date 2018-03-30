package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.activity.CatalogListActivity
import com.projectx.fisioapp.app.utils.CatalogType
import com.projectx.fisioapp.app.utils.EXTRA_CATALOG_TYPE
import com.projectx.fisioapp.domain.model.Catalog
import kotlinx.android.synthetic.main.fragment_catalog_detail.*

/**
 * A fragment representing a single Service detail screen.
 * This fragment is either contained in a [ServiceListActivity]
 * in two-pane mode (on tablets) or a [ServiceDetailActivity]
 * on handsets.
 */
class CatalogDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: Catalog? = null
    private lateinit var type: CatalogType
    var catalogItemListener: CatalogItemListener? = null
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater.let {
            root = inflater.inflate(R.layout.fragment_catalog_detail, container, false)
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        arguments?.let {

            if (arguments.containsKey(EXTRA_CATALOG_TYPE)){
                type =  arguments.getSerializable(EXTRA_CATALOG_TYPE) as CatalogType
            }

            if(arguments.containsKey(ARG_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                mItem = arguments.getSerializable(ARG_ITEM) as Catalog

                mItem?.let {
                    //activity.toolbar_layout?.title = it.content
                    activity_catalog_detail_name_text.hint = it.name
                    activity_catalog_detail_desc_text.hint = it.description
                    activity_catalog_detail_price_text.hint = it.price.toString() + " €"
                }

                activity_catalog_detail_save_bttn.setOnClickListener {
                    Log.d(CatalogDetailFragment::class.java.canonicalName, "Clicked on SAVE")
                    catalogItemListener?.onSavePressed(root.rootView, mItem!!)
                }
                activity_catalog_detail_delete_bttn.setOnClickListener {
                    Log.d(CatalogDetailFragment::class.java.canonicalName, "Clicked on DELETE")
                    catalogItemListener?.onDeletePressed(root.rootView, mItem!!.id)
                }

            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM = "item_id"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    private fun commonOnAttach(context: Context?) {
        if (context is CatalogItemListener) {
            catalogItemListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        catalogItemListener = null
    }
}

interface CatalogItemListener {
    fun onSavePressed(view: View, item: Catalog)
    fun onDeletePressed(view: View, id: String)
}
