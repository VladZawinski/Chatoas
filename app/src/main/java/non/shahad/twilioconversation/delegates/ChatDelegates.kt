package non.shahad.twilioconversation.delegates

import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderIncomingChatBinding
import non.shahad.twilioconversation.databinding.ViewholderIncomingImageBinding
import non.shahad.twilioconversation.databinding.ViewholderOutgoingChatBinding
import non.shahad.twilioconversation.databinding.ViewholderOutgoingImageBinding
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.model.MessageUiModel

fun incomingTextItem() = adapterDelegateViewBinding<MessageUiModel, MessageUiModel, ViewholderIncomingChatBinding>(
    {inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_incoming_chat,root,false)},
    on = { message, _, _ ->
        message.type == "incoming-text"
    }
){
    bind {
        binding.message = item
    }
}

fun outgoingTextItem() = adapterDelegateViewBinding<MessageUiModel, MessageUiModel, ViewholderOutgoingChatBinding>(
    {inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_outgoing_chat,root,false)},
    on = { message, _, _ ->
        message.type == "outgoing-text"
    }
){
    bind {
        binding.message = item
    }
}

fun incomingImageItem() = adapterDelegateViewBinding<MessageUiModel, MessageUiModel, ViewholderIncomingImageBinding>(
    {inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_incoming_image,root,false)},
    on = { message, _, _ ->
        message.type == "incoming-image"
    }
){
    bind {
        binding.message = item
    }
}

fun outgoingImageItem() = adapterDelegateViewBinding<MessageUiModel, MessageUiModel, ViewholderOutgoingImageBinding>(
    {inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_outgoing_image,root,false)},
    on = { message, _, _ ->
        message.type == "outgoing-image"
    }
){
    bind {
        binding.message = item
    }
}
