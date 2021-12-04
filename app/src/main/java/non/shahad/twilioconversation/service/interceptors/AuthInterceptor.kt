package non.shahad.twilioconversation.service.interceptors

import non.shahad.twilioconversation.persistence.SharedPreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefHelper: SharedPreferenceHelper
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()
            .addHeader("Authorization",prefHelper.getToken()!!)
            .addHeader("Accept","application/json")
            .addHeader("Content-Type","application/json")

        return chain.proceed(requestBuilder.build())
    }
}