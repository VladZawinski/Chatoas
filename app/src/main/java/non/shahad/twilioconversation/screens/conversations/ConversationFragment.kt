package non.shahad.twilioconversation.screens.conversations

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversationFragment: Fragment(){
    private val viewModel by viewModels<ConversationViewModel>()

}