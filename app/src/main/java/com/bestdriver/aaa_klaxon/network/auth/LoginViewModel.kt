package com.bestdriver.aaa_klaxon.network.auth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import com.bestdriver.aaa_klaxon.network.auth.AuthApiService
import com.bestdriver.aaa_klaxon.network.auth.LoginRequest

class LoginViewModel(private val authApiService: AuthApiService) : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _loginError = MutableLiveData("")
    val loginError: LiveData<String> get() = _loginError

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick(onSuccess: (String) -> Unit) {
        val email = _email.value.orEmpty()
        val password = _password.value.orEmpty()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val response = authApiService.login(LoginRequest(email, password))
                    if (response.isSuccessful) {
                        val result = response.body()?.result
                        result?.let {
                            onSuccess(it.refreshToken) // 로그인 성공 시 refreshToken 반환
                        }
                    } else {
                        _loginError.value = "로그인 실패: ${response.code()}"
                    }
                } catch (e: HttpException) {
                    _loginError.value = "서버 오류: ${e.message()}"
                } catch (e: Exception) {
                    _loginError.value = "알 수 없는 오류 발생: ${e.localizedMessage}"
                }
            }
        } else {
            _loginError.value = "아이디와 비밀번호를 입력해 주세요."
        }
    }
}
