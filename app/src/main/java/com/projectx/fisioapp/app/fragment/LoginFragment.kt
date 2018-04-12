package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.projectx.fisioapp.R


class LoginFragment : Fragment() {

    var onFragmentButtonLoginPressedListener: OnFragmentButtonLoginPressedListener? = null
    var loginButton: Button? = null
    var email: EditText? = null
    var pass: EditText? = null

    companion object {
        private val TAG = "LoginFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater?.inflate(R.layout.fragment_login, container, false)

        loginButton = root?.findViewById<Button>(R.id.btnAuthenticate)
        email = root?.findViewById<EditText>(R.id.etEmail)
        pass = root?.findViewById<EditText>(R.id.etPass)

        loginButton?.setOnClickListener {
            onFragmentButtonLoginPressedListener?.buttonLoginPressed(
                    email?.text.toString(),
                    pass?.text.toString()
            )
        }

        setFakeDataInForm()

        return root

    }

    fun setFakeDataInForm() {
        email?.setText( "fisio@invalid.com")
        pass?.setText("12345678")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    fun commonOnAttach(context: Context?) {
        if (context is OnFragmentButtonLoginPressedListener) {
            onFragmentButtonLoginPressedListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onFragmentButtonLoginPressedListener = null
    }

    interface OnFragmentButtonLoginPressedListener {
        fun buttonLoginPressed(email: String, password: String)
    }

}