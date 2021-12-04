package non.shahad.twilioconversation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.databinding.ActivityMainBinding
import non.shahad.twilioconversation.events.NavCalls
import non.shahad.twilioconversation.persistence.SharedPreferenceHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavCalls {

    @Inject lateinit var preferenceHelper: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        makeStatusBarWhite()
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        if (preferenceHelper.isLoggedIn()) {
            findNavController(R.id.navContainer).navigate(R.id.mainFragment)
        }else {
            findNavController(R.id.navContainer).navigate(R.id.authFragment)
        }

    }

    override fun switchToMain() {
        findNavController(R.id.navContainer).navigate(R.id.mainFragment)
    }

    override fun switchToAuth() {
        findNavController(R.id.navContainer).navigate(R.id.authFragment)
    }

    fun makeStatusBarWhite(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun changeStatusBarColor(color: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,color)
        }
    }

}