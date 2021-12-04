package non.shahad.twilioconversation.screens.main.users

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.databinding.FragmentUsersBinding
import non.shahad.twilioconversation.groupie.UserItem
import timber.log.Timber

@AndroidEntryPoint
class UserFragment(override val layoutRes: Int = R.layout.fragment_users) : OrbitMVIFragment<FragmentUsersBinding,UserViewModel,UserState, UserSideEffect>(){

    override val viewModel: UserViewModel by viewModels()

    private lateinit var alert: AlertDialog

    private val userAdapter = GroupieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDialog()
        viewBinding.userRv.adapter = userAdapter
    }

    override fun handleSideEffect(sideEffect: UserSideEffect){
        when(sideEffect){
            UserSideEffect.HideLoading -> {

            }
            is UserSideEffect.ShowError -> {
                Timber.d("${sideEffect.message}")
            }
            UserSideEffect.ShowLoading -> {

            }
            is UserSideEffect.ChatRoomCreated -> {
                alert.hide()
                findNavController().navigate(R.id.actionUserToChat, bundleOf("conversationId" to sideEffect.conversationId))
            }
            UserSideEffect.CreatingChatRoom -> {
                alert.show()
            }
        }
    }

    private fun createDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.layout_loading)
        alert = builder.create()
        alert.setCanceledOnTouchOutside(false)
    }

    override fun render(state: UserState) {
        userAdapter.addAll(state.users.map { UserItem(it, onChatClick = { user -> viewModel.createChatRoom(user.id) }) })
    }

}