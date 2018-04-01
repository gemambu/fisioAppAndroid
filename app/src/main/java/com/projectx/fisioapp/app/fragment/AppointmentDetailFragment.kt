package com.projectx.fisioapp.app.fragment

import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.projectx.fisioapp.R
import com.projectx.fisioapp.domain.model.Appointment
import com.squareup.picasso.Picasso

class AppointmentDetailFragment : Fragment() {

    companion object {
        private val EXTRA_APPOINTMENT = "EXTRA_APPOINTMENT"

        fun newInstance(appointment: Appointment): AppointmentDetailFragment{
            val fragment = AppointmentDetailFragment()
            val arguments = Bundle()
            arguments.putSerializable(EXTRA_APPOINTMENT, appointment)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View
    lateinit var appointmentDetail: Appointment
    lateinit var mapImage: ImageView
    lateinit var name: TextView
    lateinit var time: TextView
    lateinit var price: TextView
    lateinit var address: TextView
    lateinit var extraInfo: TextView




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        inflater?.let{
            root = it.inflate(R.layout.fragment_appointment_detail, container, false)

            appointmentDetail = arguments.getSerializable(EXTRA_APPOINTMENT) as Appointment

            name = root.findViewById(R.id.appointment_detail_customer_name_label)
            name.text = appointmentDetail.customerName

            time = root.findViewById(R.id.appointment_detail_time_label)
            time.text = appointmentDetail.date.toString()

            price = root.findViewById(R.id.appointment_detail_customer_price_label)
            price.text = appointmentDetail.servicePrice

            address = root.findViewById(R.id.appointment_detail_address_label)
            address.text = appointmentDetail.address

            extraInfo = root.findViewById(R.id.appointment_detail_extra_info_label)
            if (appointmentDetail.extraInfo.isNotEmpty()){
                extraInfo.text = appointmentDetail.extraInfo
            } else {
                extraInfo.text = "No extra info provided"
            }

            mapImage = root.findViewById(R.id.appointment_detail_map_image)
            val url = "http://maps.googleapis.com/maps/api/staticmap?center=${appointmentDetail.latitude},${appointmentDetail.longitude}&zoom=16&size=320x220&scale=2&markers=color:blue%7C${appointmentDetail.latitude},${appointmentDetail.longitude}"
            Picasso.with(activity).load(url).into(mapImage)

            mapImage.setOnClickListener(View.OnClickListener {

                val uri = Uri.parse("google.navigation:q=${appointmentDetail.latitude},${appointmentDetail.longitude}&mode=w")
                val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            })
        }

        return root
    }

}
