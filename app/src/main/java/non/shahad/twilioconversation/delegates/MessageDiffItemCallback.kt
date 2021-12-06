package non.shahad.twilioconversation.delegates

import androidx.recyclerview.widget.DiffUtil
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

object MessageDiffItemCallback: DiffUtil.ItemCallback<MessageUiModel>() {
    override fun areItemsTheSame(oldItem: MessageUiModel, newItem: MessageUiModel): Boolean {
        return oldItem.body == newItem.body
    }

    override fun areContentsTheSame(oldItem: MessageUiModel, newItem: MessageUiModel): Boolean {
        return oldItem.type == newItem.type
    }
}