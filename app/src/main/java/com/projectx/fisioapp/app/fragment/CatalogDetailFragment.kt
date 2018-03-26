package com.projectx.fisioapp.app.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.activity.dummy.DummyContent
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

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater.let {
            root= inflater.inflate(R.layout.fragment_catalog_detail, container, false)
        }

        return root
    }

   override fun onResume() {
        super.onResume()

        arguments?.let {
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
}
