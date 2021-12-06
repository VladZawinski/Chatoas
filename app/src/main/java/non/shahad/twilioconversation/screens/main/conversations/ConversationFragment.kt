package non.shahad.twilioconversation.screens.main.conversations

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.databinding.FragmentConversationsBinding
import non.shahad.twilioconversation.groupie.ConversationItem
import timber.log.Timber

@AndroidEntryPoint
class ConversationFragment(
    override val layoutRes: Int = R.layout.fragment_conversations
) : OrbitMVIFragment<FragmentConversationsBinding,ConversationViewModel,ConversationState,ConversationSideEffect>(){

    override val viewModel: ConversationViewModel
        by viewModels()

    private val groupieAdapter = GroupieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.conversationRV.adapter = groupieAdapter
    }

    override fun handleSideEffect(sideEffect: ConversationSideEffect) {
        when(sideEffect){
            ConversationSideEffect.Loading -> {

            }
            is ConversationSideEffect.ShowError -> {
                Timber.d("${sideEffect.message}")
            }
        }
    }

    override fun render(state: ConversationState) {
        groupieAdapter.update(state.conversations.map {
            ConversationItem(it, onClick = { conversation ->
                findNavController().navigate(
                    R.id.actionConversationToChat,
                    bundleOf("conversationId" to conversation.conversationSid, "peer" to it.peer.name, "chatServiceSid" to it.chatServiceSid)
                )
            })
        })
    }

}