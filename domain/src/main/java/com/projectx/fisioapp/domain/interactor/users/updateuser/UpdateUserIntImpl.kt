package com.projectx.fisioapp.domain.interactor.users.updateuser

import android.content.Context
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.model.User
import com.projectx.fisioapp.repository.RepositoryIntImpl
import com.projectx.fisioapp.repository.RepositoryInteractor
import com.projectx.fisioapp.repository.entitymodel.user.UserData
import java.lang.ref.WeakReference


class UpdateUserIntImpl(context: Context) : UpdateUserInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: RepositoryInteractor = RepositoryIntImpl(weakContext.get() !!)

    override fun execute(token: String, user: User, success: (ok: Boolean, user: User) -> Unit, error: ErrorCompletion) {

        val userData = entityMapper(user)

        repository.updateUser(
                token, userData,
                success = { ok: Boolean, userData: UserData ->
                    val user: User = entityMapper(userData)
                    success(ok, user)
                }, error = {
                    error(it)
                }
        )
    }

    private fun entityMapper(userData: UserData): User {
        val user = User(
                userData.id,
                userData.name,
                userData.lastName,
                userData.email,
                userData.isProfessional,
                userData.fellowshipNumber,
                userData.gender,
                userData.address,
                userData.phone,
                userData.birthDate,
                userData.nationalId,
                userData.registrationDate,
                userData.lastLoginDate
        )
        return user
    }

    private fun entityMapper(user: User): UserData {
        val userData = UserData(
                user.id,
                user.name,
                user.lastName,
                user.email,
                user.isProfessional,
                user.fellowshipNumber,
                user.gender,
                user.address,
                user.phone,
                user.birthDate,
                user.nationalId,
                user.registrationDate,
                user.lastLoginDate
        )
        return userData
    }

}