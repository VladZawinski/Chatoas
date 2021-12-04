package non.shahad.twilioconversation.screens.main.login

import dagger.hilt.android.lifecycle.HiltViewModel
import non.shahad.twilioconversation.base.OrbitMVIViewModel
import non.shahad.twilioconversation.persistence.SharedPreferenceHelper
import non.shahad.twilioconversation.service.repository.LoginRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferenceHelper: SharedPreferenceHelper,
    private val repository: LoginRepository
): OrbitMVIViewModel<LoginState,LoginSideEffect>() {

    override val container: Container<LoginState, LoginSideEffect>
        = container(LoginState())

    fun login(email: String,password: String) = intent {
        try {
            postSideEffect(LoginSideEffect.LoggingIn)
            val result = repository.login(email, password)
            preferenceHelper.putToken(result.token)
            preferenceHelper.setLogInStatus(true)
            postSideEffect(LoginSideEffect.LogInSuccess)
        }catch (e: Throwable){
            postSideEffect(LoginSideEffect.LogInFailed)
        }
    }

}

data class LoginState(
    val any: Any = Any()
)

sealed class LoginSideEffect {
    object LoggingIn: LoginSideEffect()
    object LogInSuccess: LoginSideEffect()
    object LogInFailed: LoginSideEffect()
}