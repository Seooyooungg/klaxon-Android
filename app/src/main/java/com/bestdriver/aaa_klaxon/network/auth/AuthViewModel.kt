package com.bestdriver.aaa_klaxon.network.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authApiService: AuthApiService
) : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _nickname = MutableLiveData("")
    val nickname: LiveData<String> get() = _nickname

    private val _car_number = MutableLiveData("")
    val car_number: LiveData<String> get() = _car_number

    private val _phone_number = MutableLiveData("")
    val phone_number: LiveData<String> get() = _phone_number

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> get() = _showDialog

    private val _dialogMessage = MutableLiveData("")
    val dialogMessage: LiveData<String> get() = _dialogMessage

    fun onSignUpClick(navController: NavController) {
        val email = _email.value.orEmpty()
        val password = _password.value.orEmpty()
        val nickname = _nickname.value.orEmpty()
        val car_number = _car_number.value.orEmpty()
        val phone_number = _phone_number.value.orEmpty()

        if (email.isNotEmpty() && password.isNotEmpty() && nickname.isNotEmpty() && car_number.isNotEmpty() && phone_number.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val request = SignUpRequest(email, password, nickname, car_number, phone_number)
                    val response = authApiService.signUp(request)
                    if (response.isSuccessful) {
                        navController.popBackStack()
                    } else {
                        _dialogMessage.value = "회원가입에 실패했습니다: ${response.code()}"
                        _showDialog.value = true
                    }
                } catch (e: HttpException) {
                    _dialogMessage.value = "회원가입에 실패했습니다: ${e.message()}"
                    _showDialog.value = true
                } catch (e: Exception) {
                    _dialogMessage.value = "알 수 없는 오류가 발생했습니다."
                    _showDialog.value = true
                }
            }
        } else {
            _dialogMessage.value = "모든 필드를 올바르게 입력해 주세요."
            _showDialog.value = true
        }
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun updateCarNumber(newCarNumber: String) {
        _car_number.value = newCarNumber
    }

    fun updatePhoneNumber(newPhoneNumber: String) {
        _phone_number.value = newPhoneNumber
    }

    fun updateDialogMessage(message: String) {
        _dialogMessage.value = message
    }

    fun showErrorDialog() {
        _showDialog.value = true
    }

    fun hideErrorDialog() {
        _showDialog.value = false
    }
}
