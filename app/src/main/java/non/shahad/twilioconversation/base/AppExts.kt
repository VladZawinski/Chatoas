package non.shahad.twilioconversation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar

fun Fragment.addBackButtonIfToolbarExists(toolbar: MaterialToolbar){
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    toolbar.setNavigationOnClickListener {
        findNavController().navigateUp()
    }

}

fun Fragment.addBackButtonWithEmptyTitle(toolbar: MaterialToolbar){
    (requireActivity() as AppCompatActivity).apply {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    toolbar.setNavigationOnClickListener {
        findNavController().navigateUp()
    }
}