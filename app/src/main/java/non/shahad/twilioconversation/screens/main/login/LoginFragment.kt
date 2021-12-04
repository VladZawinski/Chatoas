package non.shahad.twilioconversation.screens.main.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import non.shahad.twilioconversation.R
import non.shahad.twilioconversation.base.OrbitMVIFragment
import non.shahad.twilioconversation.base.addBackButtonIfToolbarExists
import non.shahad.twilioconversation.databinding.FragmentLoginBinding
import non.shahad.twilioconversation.events.NavCalls

@AndroidEntryPoint
class LoginFragment(
    override val layoutRes: Int = R.layout.fragment_login
) : OrbitMVIFragment<FragmentLoginBinding,LoginViewModel,LoginState, LoginSideEffect>() {

    private lateinit var alert: AlertDialog

    override val viewModel: LoginViewModel
        by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBackButtonIfToolbarExists(viewBinding.toolbar)
        createDialog()

        viewBinding.loginBtn.setOnClickListener {
            val email = viewBinding.emailEditText.text.toString().trim()
            val password = viewBinding.passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                viewModel.login(email,password)
            }else{
                Snackbar.make(viewBinding.root,"Fill something fucktard!!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun handleSideEffect(sideEffect: LoginSideEffect) {
        when(sideEffect){
            LoginSideEffect.LogInFailed -> {
                alert.hide()
                Snackbar.make(viewBinding.root,"Login failed", Snackbar.LENGTH_LONG).show()
            }
            LoginSideEffect.LogInSuccess -> {
                alert.hide()
                Snackbar.make(viewBinding.root,"Login success", Snackbar.LENGTH_LONG).show()
                (requireActivity() as NavCalls).switchToMain()
            }
            LoginSideEffect.LoggingIn -> {
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

    override fun render(state: LoginState) {
        // Nothing to render
    }

}