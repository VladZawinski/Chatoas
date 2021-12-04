package non.shahad.twilioconversation.screens.conversations

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(

): ContainerHost<ConversationState,ConversationSideEffect>,ViewModel() {
    override val container: Container<ConversationState, ConversationSideEffect>
        = container(ConversationState())

}

data class ConversationState(
    val conversations: List<String> = emptyList()
)

sealed class ConversationSideEffect {

}