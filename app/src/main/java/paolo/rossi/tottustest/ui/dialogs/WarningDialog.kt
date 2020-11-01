package paolo.rossi.tottustest.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import paolo.rossi.tottustest.R


open class WarningDialog(context: Context,
                         dismissible: Boolean,
                         private val with_deny_option: Boolean,
                         private val title: String,
                         private val message: String,
                         private val acceptance_title: String,
                         private val acceptance_function: (() -> Unit)?,
                         private val denial_title: String,
                         private val denial_function: (() -> Unit)? )
    : Dialog(context, R.style.AppCompatAlertDialogStyle) {

    private lateinit var tv_title : TextView
    private lateinit var tv_message : TextView
    private lateinit var b_denial : AppCompatButton
    private lateinit var b_acceptance : AppCompatButton

    init {
        this.setCancelable(dismissible)
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_warning)

        setElements()
        setActions()
    }


    private fun setElements() {
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = title

        tv_message = findViewById(R.id.tv_message)
        tv_message.text = message

        b_denial = findViewById(R.id.b_denial)
        b_denial.text = denial_title
        if (!with_deny_option) b_denial.visibility = View.GONE

        b_acceptance = findViewById(R.id.b_acceptance)
        b_acceptance.text = acceptance_title
    }


    private fun setActions() {
        b_acceptance.setOnClickListener {
            acceptance_function?.invoke()
            this.dismiss()
        }

        b_denial.setOnClickListener {
            denial_function?.invoke()
            this.dismiss()
        }
    }
}