package paolo.rossi.tottustest.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.tottustest.R


open class AddMemberBottomDialog(context: Context, private val team_name: String,  private val on_member_creation_clicked: (String, String) -> (Unit)) : BottomSheetDialog(context) {

    private var tv_team_name : TextView? = null
    private lateinit var et_name : EditText
    private lateinit var et_email : EditText

    private lateinit var b_out : AppCompatButton
    private lateinit var b_create : AppCompatButton


    init {
        this.setCancelable(false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_dialog_add_member)

        setElements()
        setActions()
    }


    private fun setElements() {
        tv_team_name = findViewById(R.id.tv_team_name)!!
        tv_team_name?.setText("Registrar miembro de los/las $team_name")

        et_name = findViewById(R.id.et_name)!!
        et_email = findViewById(R.id.et_email)!!
        b_out = findViewById(R.id.b_out)!!
        b_create = findViewById(R.id.b_create)!!
    }


    private fun setActions() {
        b_out.setOnClickListener {
            this.hide()
        }

        b_create.setOnClickListener {
            if(et_name.text.toString().length > 100) {
                Snackbar.make(findViewById(android.R.id.content)!!, "El nombre no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
                et_name.requestFocus()
                return@setOnClickListener
            } else if (et_name.text.toString().isBlank()) {
                Snackbar.make(findViewById(android.R.id.content)!!, "El nombre no puede estar vacío", Snackbar.LENGTH_LONG).show()
                et_name.requestFocus()
                return@setOnClickListener
            }

            if(et_email.text.toString().length > 100) {
                Snackbar.make(findViewById(android.R.id.content)!!, "El correo no puede medir más de 100 caracteres", Snackbar.LENGTH_LONG).show()
                et_email.requestFocus()
                return@setOnClickListener
            } else if (et_email.text.toString().isBlank()) {
                Snackbar.make(findViewById(android.R.id.content)!!, "El correo no puede estar vacía", Snackbar.LENGTH_LONG).show()
                et_email.requestFocus()
                return@setOnClickListener
            }

            on_member_creation_clicked.invoke(et_name.text.toString(), et_email.text.toString())
            this.dismiss()
        }
    }

}