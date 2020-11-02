package paolo.rossi.tottustest.ui.activities.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.data.models.User
import paolo.rossi.tottustest.databinding.ActivityLoginBinding
import paolo.rossi.tottustest.interfaces.Constants
import paolo.rossi.tottustest.ui.activities.main.MainIntent
import paolo.rossi.tottustest.ui.activities.account_creation.AccountCreationIntent


fun Context.LoginIntent(): Intent {
    return Intent(this, LoginActivity::class.java).apply {
    }
}


class LoginActivity : AppCompatActivity() {

    private lateinit var view_model : LoginViewModel

    private lateinit var binding : ActivityLoginBinding
    private lateinit var self: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setElements()
    }


    private fun setElements() {
        self = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        view_model = ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    fun tryToLogin(view: View) {
        if(binding.etEmail.text.toString().length > 100) {
            Snackbar.make(findViewById(android.R.id.content), "El correo no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
            binding.etEmail.requestFocus()
            return
        } else if (binding.etEmail.text.toString().isBlank()) {
            Snackbar.make(findViewById(android.R.id.content), "El correo no puede estar vacío", Snackbar.LENGTH_LONG).show()
            binding.etEmail.requestFocus()
            return
        }

        if(binding.etPassword.text.toString().length > 100) {
            Snackbar.make(findViewById(android.R.id.content), "La contraseña no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
            binding.etPassword.requestFocus()
            return
        } else if (binding.etPassword.text.toString().isBlank()) {
            Snackbar.make(findViewById(android.R.id.content), "La contraseña no puede estar vacía", Snackbar.LENGTH_SHORT).show()
            binding.etPassword.requestFocus()
            return
        }

        val user = view_model.tryLogin(email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString())
        if (user != null) {
            startActivity(MainIntent())
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Las credenciales no coinciden", Snackbar.LENGTH_LONG).setAction("OK", null).show()
        }
    }


    fun goToAccountCreation(view: View) {
        startActivityForResult(AccountCreationIntent(), Constants.REQUEST_CODE_ACCOUNT_CREATION)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // If you have multiple activities returning results then you should include unique request codes for each
        if (requestCode == Constants.REQUEST_CODE_ACCOUNT_CREATION) {
            // The result code from the activity started using startActivityForResults
            if (resultCode == Activity.RESULT_OK) {
                val user = data?.getParcelableExtra<User>(Constants.INTENT_NEW_USER)
                binding.etEmail.setText(user?.email)
                binding.etPassword.setText(user?.password)
            }
        }
    }

}