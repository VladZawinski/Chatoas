package non.shahad.twilioconversation.screens.main.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.FragmentMainBinding

class MainFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false).apply {
            val mainNavController = root.findViewById<View>(R.id.navArea).findNavController()
            bottomNavigationView.setupWithNavController(mainNavController)
            bottomNavigationView.setOnItemReselectedListener {  }
            mainNavController.addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.chatFragment){
                    bottomNavigationView.visibility = View.GONE
                }else {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        return binding.root
    }

}