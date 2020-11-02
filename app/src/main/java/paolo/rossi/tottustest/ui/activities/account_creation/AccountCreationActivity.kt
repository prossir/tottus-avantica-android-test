package paolo.rossi.tottustest.ui.activities.account_creation

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
import paolo.rossi.tottustest.databinding.ActivityAccountCreationBinding
import paolo.rossi.tottustest.interfaces.Constants


fun Context.AccountCreationIntent(): Intent {
    return Intent(this, AccountCreationActivity::class.java).apply {
    }
}


class AccountCreationActivity : AppCompatActivity() {

    private lateinit var view_model : AccountCreationViewModel

    private lateinit var binding : ActivityAccountCreationBinding
    private lateinit var self: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_creation)

        setElements()
    }


    private fun setElements() {
        self = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_creation)
        view_model = ViewModelProvider(this).get(AccountCreationViewModel::class.java)
    }


    fun trySignUp(view: View) {
        if(binding.etName.text.toString().length > 100) {
            Snackbar.make(findViewById(android.R.id.content), "El nombre no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
            binding.etName.requestFocus()
            return
        } else if (binding.etName.text.toString().isBlank()) {
            Snackbar.make(findViewById(android.R.id.content), "El nombre no puede estar vacío", Snackbar.LENGTH_LONG).show()
            binding.etName.requestFocus()
            return
        }

        if(binding.etLastName.text.toString().length > 100) {
            Snackbar.make(findViewById(android.R.id.content), "El apellido no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
            binding.etLastName.requestFocus()
            return
        } else if (binding.etLastName.text.toString().isBlank()) {
            Snackbar.make(findViewById(android.R.id.content), "El apellido no puede estar vacío", Snackbar.LENGTH_LONG).show()
            binding.etLastName.requestFocus()
            return
        }

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
            Snackbar.make(findViewById(android.R.id.content), "La contraseña no puede estar vacía", Snackbar.LENGTH_LONG).show()
            binding.etPassword.requestFocus()
            return
        }

        val user = view_model.createUser(name = binding.etName.text.toString(),
            last_name = binding.etLastName.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )

        if (user != null) {
            val intent = Intent()
            intent.putExtra(Constants.INTENT_NEW_USER, user)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Hubo un error al crear el usuario. No se pudo crear", Snackbar.LENGTH_LONG).setAction("Ok", null).show()
        }
    }


    fun toPrevious(view: View) {
        finish()
    }
}