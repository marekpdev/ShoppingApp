package com.marekpdev.shoppingapp.ui.registration

import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.domain.RegisterUserResponse
import com.marekpdev.shoppingapp.extensions.asLiveData
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
class RegistrationViewModel @Inject constructor(val userRepository: UserRepository): BaseViewModel(){

    private val _userRegisteredEvent = LiveEvent<Any>()
    val userRegisteredEvent = _userRegisteredEvent.asLiveData()

    fun registerUser(email: String, password: String) {
        userRepository.registerUser(email, password)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { _userRegisteredEvent.value = Any() }
            .doOnError(error::postValue)
            .subscribe()
            .addToDisposable()
    }

}