package paolo.rossi.tottustest.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R


class CreateTeamDialog(context: Context,
                       private val on_team_creation_clicked : ((String, String) -> Unit)) :
    Dialog(context, R.style.AppCompatAlertDialogStyle) {


    private lateinit var et_name : EditText
    private lateinit var et_catchphrase : EditText

    private lateinit var b_out : AppCompatButton
    private lateinit var b_create : AppCompatButton

    init {
        this.setCancelable(false)
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_create_team)
        setElements()
        setActions()
    }


    private fun setElements() {
        et_name = findViewById(R.id.et_name)
        et_catchphrase = findViewById(R.id.et_catchphrase)
        b_out = findViewById(R.id.b_out)
        b_create = findViewById(R.id.b_create)
    }


    private fun setActions() {
        b_out.setOnClickListener {
            this.hide()
        }

        b_create.setOnClickListener {
            if(et_name.text.toString().length > 100) {
                Snackbar.make(findViewById(android.R.id.content), "El nombre no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
                et_name.requestFocus()
                return@setOnClickListener
            } else if (et_name.text.toString().isBlank()) {
                Snackbar.make(findViewById(android.R.id.content), "El nombre no puede estar vacío", Snackbar.LENGTH_LONG).show()
                et_name.requestFocus()
                return@setOnClickListener
            }

            if(et_catchphrase.text.toString().length > 500) {
                Snackbar.make(findViewById(android.R.id.content), "La frase caracteristica no puede medir más de 500 caracteres", Snackbar.LENGTH_LONG).show()
                et_catchphrase.requestFocus()
                return@setOnClickListener
            } else if (et_catchphrase.text.toString().isBlank()) {
                Snackbar.make(findViewById(android.R.id.content), "La frase caracteristica no puede estar vacía", Snackbar.LENGTH_LONG).show()
                et_catchphrase.requestFocus()
                return@setOnClickListener
            }

            this.hide()
            on_team_creation_clicked.invoke(et_name.text.toString(), et_catchphrase.text.toString())
        }
    }

}