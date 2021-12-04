package non.shahad.twilioconversation.screens.main.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.base.addBackButtonIfToolbarExists
import non.shahad.twilioconversation.databinding.FragmentChatBinding
import non.shahad.twilioconversation.groupie.chat.IncomingChatItem
import non.shahad.twilioconversation.groupie.chat.OutgoingChatItem
import timber.log.Timber

@AndroidEntryPoint
class ChatFragment(
    override val layoutRes: Int = R.layout.fragment_chat
) : OrbitMVIFragment<FragmentChatBinding,ChatViewModel,ChatState,ChatSideEffect>(){

    override val viewModel: ChatViewModel by viewModels()

    private val chatAdapter = GroupieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.chatRV.adapter = chatAdapter
        arguments?.getString("conversationId")?.let {
            viewModel.fetchMessages(it)
        }
        arguments?.getString("peer")?.let {
            addBackButtonIfToolbarExists(viewBinding.toolbar)
            viewBinding.toolbar.title = it
        }

        viewBinding.btnSendMessage.setOnClickListener {
            viewBinding.edtMsg.text.toString().also { msg ->
                if (msg.isNotEmpty()){
                    viewModel.sendMessage(msg)
                    viewBinding.edtMsg.text.clear()
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: ChatSideEffect) {
        when(sideEffect){
            ChatSideEffect.MessageSent -> {

            }
            ChatSideEffect.Pooling -> {
                Timber.d("$ Pooling")
            }
        }
    }

    override fun render(state: ChatState) {
        chatAdapter.update(
            state.messages.map {
                return@map if (it.type == "incoming"){
                    IncomingChatItem(it)
                }else {
                    OutgoingChatItem(it)
                }
            }
        )
    }

}