package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.persistence.SharedPreferenceHelper
import non.shahad.twilioconversation.service.TwilioService
import non.shahad.twilioconversation.service.request.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val service: TwilioService
) {

    suspend fun login(email: String,password: String) = service.login(LoginRequest(email, password))

}