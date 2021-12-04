package non.shahad.twilioconversation.groupie

import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderConversationBinding
import non.shahad.twilioconversation.databinding.ViewholderUserBinding
import non.shahad.twilioconversation.service.model.Conversation
import non.shahad.twilioconversation.service.model.User

class ConversationItem constructor(
    private val conversation: Conversation,
    private val onClick: (Conversation) -> Unit
): BindableItem<ViewholderConversationBinding>() {

    override fun bind(binding: ViewholderConversationBinding, position: Int) {
        binding.conversation = conversation
        binding.materialButton.setOnClickListener { onClick(conversation) }
    }

    override fun getLayout(): Int = R.layout.viewholder_conversation
}