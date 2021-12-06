package non.shahad.twilioconversation.screens.main.chat

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.base.addBackButtonIfToolbarExists
import non.shahad.twilioconversation.databinding.FragmentChatBinding
import non.shahad.twilioconversation.databinding.ViewholderIncomingChatBinding
import non.shahad.twilioconversation.delegates.*
import non.shahad.twilioconversation.groupie.chat.IncomingChatItem
import non.shahad.twilioconversation.groupie.chat.IncomingImageItem
import non.shahad.twilioconversation.groupie.chat.OutgoingChatItem
import non.shahad.twilioconversation.groupie.chat.OutgoingImageItem
import non.shahad.twilioconversation.service.model.Message
import timber.log.Timber

@AndroidEntryPoint
class ChatFragment(
    override val layoutRes: Int = R.layout.fragment_chat
) : OrbitMVIFragment<FragmentChatBinding,ChatViewModel,ChatState,ChatSideEffect>(){

    override val viewModel: ChatViewModel by viewModels()

    private val chatAdapter = GroupieAdapter()

    private val delegate = AsyncListDifferDelegationAdapter(
        MessageDiffItemCallback,
        incomingTextItem(),
        outgoingImageItem(),
        outgoingTextItem(),
        incomingImageItem()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.chatRV.adapter = delegate
//        viewBinding.chatRV.itemAnimator = null

        arguments?.getString("conversationId")?.let { conversationSid ->
            arguments?.getString("chatServiceSid")?.let { chatSid ->
                viewModel.fetchMessages(conversationSid,chatSid)
            }
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
                    viewBinding.chatRV.scrollToPosition(0)
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

            ChatSideEffect.FakeSent -> {
                viewBinding.chatRV.scrollToPosition(0)
            }
        }
    }

    override fun render(state: ChatState) {
        Timber.d("${state.messages}")
        delegate.items = state.messages
        viewBinding.chatRV.scrollToPosition(0)
//        chatAdapter.updateAsync(
//            state.messages.map {
//                when(it.inOut){
//                    "incoming" -> {
//                        if (it.type == "image"){
//                            return@map IncomingImageItem(it)
//                        }else {
//                            return@map IncomingChatItem(it)
//                        }
//                    }
//                    "outgoing" -> {
//                        if (it.type == "image"){
//                            return@map OutgoingImageItem(it)
//                        }else {
//                            return@map OutgoingChatItem(it)
//                        }
//                    }
//                    else -> {
//                        return@map OutgoingChatItem(null)
//                    }
//                }
//            }
//        )
    }



}