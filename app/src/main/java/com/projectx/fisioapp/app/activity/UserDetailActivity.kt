package com.projectx.fisioapp.app.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.view.View
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.helper.BottomNavigationViewHelper
import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.users.getuser.GetUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.getuser.GetUserInteractor
import com.projectx.fisioapp.domain.interactor.users.updateuser.UpdateUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.updateuser.UpdateUserInteractor
import com.projectx.fisioapp.domain.model.User
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.util.*

class UserDetailActivity : ParentActivity() {

    lateinit var user: User
    var userWithChanges: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        setListeners()

        getUser()

    }

    fun setListeners() {

        btnCancel.setOnClickListener {
            ToastIt(this, "Cancel changes")
            finish()
        }

        btnSave.setOnClickListener {
            updateUser()
        }

        rbFemale.setOnClickListener {
            rbFemale.isChecked = if(rbFemale.isChecked) true else false
            rbMale.isChecked = !rbFemale.isChecked
        }

        rbMale.setOnClickListener {
            rbMale.isChecked = if(rbMale.isChecked) true else false
            rbFemale.isChecked = !rbMale.isChecked
        }

    }

    fun getUser() {
        var getUser: GetUserInteractor = GetUserIntImpl(this)

        try {
            getUser.execute(
                    token,
                    uId,
                    success = {
                        user = it
                        fillFileds(it)
                    }, error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                            ToastIt(baseContext, errorMessage)
                        }
                    })
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }

    fun updateUser() {

        var checkFields = getFieldsOrErrors()
        if (checkFields.second != null) {
            ToastIt(this, "Fields with errors")
            return
        }

        if (checkFields.first == null) {
            ToastIt(this, "No user information available")
            return
        }

        userWithChanges = checkFields.first

        var updateUser: UpdateUserInteractor = UpdateUserIntImpl(this)

        try {
            updateUser.execute(
                    token,
                    userWithChanges as User,
                    success = { ok: Boolean, user: User ->
                        if (ok) {
                            fillFileds(user)
                            ToastIt(this, "User updated")
                        }
                        else {
                            ToastIt(this, "Success/error")
                        }
                    }, error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                            ToastIt(baseContext, errorMessage)
                        }
                    })
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }

    fun fillBackgroundColorForFileds(fields: List<String>) {

        val allFields : MutableList<View> = mutableListOf(lblName, lblLastName, lblLastName, lblEmail, lblAddress, lblPhone, lblBirthdate, lblNationalID, lblFellowshipNumber,lblRegistrationDate, lblLastLoginDate, lblProfessional, lblGender)
        allFields.map { it.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields) }

        fields.map{
            when (it) {
                "lblName" -> lblName.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblLastName" -> lblLastName.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblEmail" -> lblEmail.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblAddress" -> lblAddress.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblPhone" -> lblPhone.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblBirthdate" -> lblBirthdate.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblNationalID" -> lblNationalID.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblFellowshipNumber" -> lblFellowshipNumber.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblProfessional" -> lblProfessional.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                "lblGender" -> lblGender.background = ContextCompat.getDrawable(this, R.drawable.gradient_left_column_fields_error)
                else -> ToastIt(this, it)
            }
        }
    }


    fun fillFileds(user: User) {
        txtName.setText(user.name)
        txtLastName.setText(user.lastName)
        txtEmail.setText(user.email)
        txtAddress.setText(user.address)
        txtPhone.setText(user.phone)
        txtBirthdate.setText(user.birthDate.toString())
        txtNationalID.setText(user.nationalId)
        txtFellowshipNumber.setText(user.fellowshipNumber)
        txtRegistrationDate.setText(user.registrationDate)
        txtLastLoginDate.setText(user.lastLoginDate)
        swProfesional.isChecked = user.isProfessional
        if (user.gender == "female") {
            rbFemale.isChecked = true
            rbMale.isChecked = false
        } else if (user.gender == "male") {
            rbMale.isChecked = true
            rbFemale.isChecked = false
        }
    }

    fun getFieldsOrErrors(): Pair<User?, List<String>?> {
        var fieldsWithErrors: MutableList<String> = mutableListOf()

        lateinit var gender: String
            if (rbFemale.isChecked) {
                gender = "female"
            } else if (rbMale.isChecked) {
                gender = "male"
            } else {
                fieldsWithErrors.add("lblGender")
            }

        fillBackgroundColorForFileds(fieldsWithErrors)

        if (fieldsWithErrors.size != 0) return Pair(null, fieldsWithErrors)

        val user = User(
                uId,
                txtName.text.toString(),
                txtLastName.text.toString(),
                txtEmail.text.toString(),
                swProfesional.isChecked,
                txtFellowshipNumber.text.toString(),
                gender,
                txtAddress.text.toString(),
                txtPhone.text.toString(),
                Date(),
                txtNationalID.text.toString(),
                txtRegistrationDate.text.toString(),
                txtLastLoginDate.text.toString()
        )
        return Pair(user, null)
    }

}
