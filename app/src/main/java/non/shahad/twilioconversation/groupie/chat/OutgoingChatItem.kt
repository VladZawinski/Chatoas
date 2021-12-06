package non.shahad.twilioconversation.groupie.chat

import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderIncomingChatBinding
import non.shahad.twilioconversation.databinding.ViewholderOutgoingChatBinding
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

class OutgoingChatItem constructor(
    private val message: MessageUiModel?
): BindableItem<ViewholderOutgoingChatBinding>() {

    override fun bind(binding: ViewholderOutgoingChatBinding, position: Int) {
        binding.message = message
    }

    override fun getLayout(): Int = R.layout.viewholder_outgoing_chat
}