package cn.xiaowine.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.appcompat.HyperButton
import cn.xiaowine.ui.databinding.WineDialogBinding
import cn.xiaowine.ui.tools.DrawableTools.createRoundShape
import cn.xiaowine.ui.tools.Tools.dp
import cn.xiaowine.ui.tools.Tools.getProp

open class WineDialog(context: Context) : Dialog(context, R.style.Theme_WineDialog) {
    private var _binding: WineDialogBinding? = null
    private val binding: WineDialogBinding get() = _binding!!
    val titleView: AppCompatTextView
        get() = binding.titleView
    val messageView: AppCompatTextView
        get() = binding.messageView
    val customizAreaeView: LinearLayout
        get() = binding.customizeAreaView
    val buttonAreaView: LinearLayout
        get() = binding.buttonAreaView
    val constraintLayout: ConstraintLayout
        get() = binding.constraintLayout

    init {
        _binding = WineDialogBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
//        setContentView(R.layout.wine_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConstraintSet().apply {
            clone(constraintLayout)
            val dp20 = 20.dp
            val dp12 = 12.dp
            connect(titleView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, dp20)
            connect(titleView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20)
            connect(titleView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20)
            connect(messageView.id, ConstraintSet.TOP, titleView.id, ConstraintSet.BOTTOM, dp12)
            connect(messageView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20)
            connect(messageView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20)
            connect(customizAreaeView.id, ConstraintSet.TOP, messageView.id, ConstraintSet.BOTTOM, dp12)
            connect(buttonAreaView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20)
            connect(buttonAreaView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20)
            connect(buttonAreaView.id, ConstraintSet.TOP, customizAreaeView.id, ConstraintSet.BOTTOM, dp12)
            connect(buttonAreaView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, dp20)
            applyTo(constraintLayout)
        }
        titleView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 19f)
            typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Typeface.create(null, 500, false)
            } else {
                Typeface.DEFAULT_BOLD
            }
        }
        messageView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
        }
        window?.apply {
            if (getProp("ro.build.characteristics") == "tablet" || context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setGravity(Gravity.CENTER)
                setWindowAnimations(R.style.Theme_WineDialogPadAnim)
            } else {
                setGravity(Gravity.BOTTOM)
                attributes.y = 15.dp
                setWindowAnimations(R.style.Theme_WineDialogAnim)
            }
            setBackgroundDrawable(createRoundShape(30.dp.toFloat(), ContextCompat.getColor(context, R.color.dialog_background_color)))
            attributes.apply {
                dimAmount = 0.5F
                width = 380.dp
            }
        }

    }

    override fun show() {
        super.show()
        if (buttonAreaView.childCount > 2) {
            buttonAreaView.orientation = LinearLayout.VERTICAL
        } else {
            buttonAreaView.orientation = LinearLayout.HORIZONTAL
        }
    }

    override fun dismiss() {
        super.dismiss()
        _binding = null
    }

    fun addButton(string: String, event: ((HyperButton) -> Unit)? = null): HyperButton {
        return HyperButton(context).apply {
            (layoutParams as LinearLayout.LayoutParams).apply {
                setMargins(6.dp, 10.dp, 6.dp, 6.dp)
            }
            text = string
            buttonAreaView.addView(this)
            setOnClickListener {
                if (!it.isEnabled) return@setOnClickListener
                if (event != null) {
                    event(this)
                } else {
                    dismiss()
                }
            }
        }
    }

    fun setTitle(title: String) {
        titleView.apply {
            text = title
            visibility = if (title.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    override fun setTitle(@StringRes title: Int) {
        titleView.apply {
            setText(title)
            visibility = if (title == -1) View.GONE else View.VISIBLE
        }
    }

    fun setMessage(message: String) {
        messageView.apply {
            text = message
            visibility = if (message.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    fun setMessage(@StringRes message: Int) {
        messageView.apply {
            setText(message)
            visibility = if (message == -1) View.GONE else View.VISIBLE
        }
    }

    fun addCustomizeView(customizeView: View) {
        customizAreaeView.addView(customizeView)
    }
}