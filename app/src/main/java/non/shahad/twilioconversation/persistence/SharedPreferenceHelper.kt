package non.shahad.twilioconversation.persistence

import android.content.Context

class SharedPreferenceHelper(context: Context, prefName: String) {

    private val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun isLoggedIn(): Boolean {
        return pref.getBoolean("is-logged-in",false)
    }

    fun setLogInStatus(isLogged: Boolean){
        editor.putBoolean("is-logged-in",isLogged).apply()
    }


    fun putToken(token: String){
        editor.putString("token-app",token).apply()
    }

    fun getToken() = pref.getString("token-app","")

}