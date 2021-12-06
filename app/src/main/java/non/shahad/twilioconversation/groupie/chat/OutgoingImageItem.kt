package non.shahad.twilioconversation.groupie.chat

import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderOutgoingImageBinding
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

class OutgoingImageItem constructor(
    private val message: MessageUiModel
): BindableItem<ViewholderOutgoingImageBinding>() {

    override fun bind(binding: ViewholderOutgoingImageBinding, position: Int) {
        binding.message = message
    }

    override fun getLayout(): Int = R.layout.viewholder_outgoing_image
}