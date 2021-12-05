package non.shahad.twilioconversation.groupie

import android.view.View
import com.xwray.groupie.databinding.BindableItem
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.databinding.ViewholderUserBinding
import non.shahad.twilioconversation.service.model.User

class UserItem constructor(
    private val user: User,
    private val onChatClick: (User) -> Unit
): BindableItem<ViewholderUserBinding>() {

    override fun bind(binding: ViewholderUserBinding, position: Int) {
        binding.user = user
        if (user.isAlreadyConnected){
            binding.materialButton.visibility = View.GONE
        }
        binding.materialButton.setOnClickListener { onChatClick(user) }
    }

    override fun getLayout(): Int = R.layout.viewholder_user
}