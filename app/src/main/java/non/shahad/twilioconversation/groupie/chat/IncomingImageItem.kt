package non.shahad.twilioconversation.groupie.chat

import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderIncomingImageBinding
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

class IncomingImageItem constructor(
    private val message: MessageUiModel
): BindableItem<ViewholderIncomingImageBinding>() {

    override fun bind(binding: ViewholderIncomingImageBinding, position: Int) {
        binding.message = message
    }

    override fun getLayout(): Int = R.layout.viewholder_incoming_image
}