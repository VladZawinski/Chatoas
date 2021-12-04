package non.shahad.twilioconversation.screens.main.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.databinding.FragmentChatBinding
import timber.log.Timber

@AndroidEntryPoint
class ChatFragment(
    override val layoutRes: Int = R.layout.fragment_chat
) : OrbitMVIFragment<FragmentChatBinding,ChatViewModel,ChatState,ChatSideEffect>(){

    override val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("conversationId")?.let {
            viewModel.fetchMessages(it)
        }
    }

    override fun handleSideEffect(sideEffect: ChatSideEffect) {

    }

    override fun render(state: ChatState) {
        Timber.d("$state")
    }

}