package non.shahad.twilioconversation.groupie.chat

import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderConversationBinding
import non.shahad.twilioconversation.databinding.ViewholderIncomingChatBinding
import non.shahad.twilioconversation.service.model.Conversation
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

class IncomingChatItem constructor(
    private val message: MessageUiModel
): BindableItem<ViewholderIncomingChatBinding>() {

    override fun bind(binding: ViewholderIncomingChatBinding, position: Int) {
        binding.message = message
    }

    override fun getLayout(): Int = R.layout.viewholder_incoming_chat
}