package non.shahad.twilioconversation.screens.main.users

import dagger.hilt.android.lifecycle.HiltViewModel
import non.shahad.twilioconversation.base.OrbitMVIViewModel
import non.shahad.twilioconversation.service.model.User
import non.shahad.twilioconversation.service.repository.UserRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): OrbitMVIViewModel<UserState, UserSideEffect>() {

    override val container: Container<UserState, UserSideEffect>
        = container(UserState()) {
            fetch()
    }

    private fun fetch() = intent {
        postSideEffect(UserSideEffect.ShowLoading)

        try {
            val result = userRepository.fetchAvailableUser()

            reduce {
                state.copy(users = result)
            }

            postSideEffect(UserSideEffect.HideLoading)
        }catch (e: Throwable){
            postSideEffect(UserSideEffect.ShowError(e.message!!))
        }
    }

    fun createChatRoom(userId: String) = intent {
        try {
            postSideEffect(UserSideEffect.CreatingChatRoom)
            val result = userRepository.connect(userId)
            postSideEffect(UserSideEffect.ChatRoomCreated(result.conversationId))
        }catch (e: Throwable){
            postSideEffect(UserSideEffect.ShowError(e.message!!))
        }
    }

}

data class UserState(
    val users: List<User> = emptyList()
)

sealed class UserSideEffect {
    data class ShowError(val message: String): UserSideEffect()
    object ShowLoading: UserSideEffect()
    object HideLoading: UserSideEffect()
    object CreatingChatRoom: UserSideEffect()
    data class ChatRoomCreated(val conversationId: String): UserSideEffect()
}