package non.shahad.twilioconversation.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class OrbitMVIViewModel<VS: Any,SE: Any>: ContainerHost<VS, SE>, ViewModel(){

}