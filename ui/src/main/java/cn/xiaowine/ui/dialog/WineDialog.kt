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
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools.createRoundShape
import cn.xiaowine.ui.Tools.dp2px
import cn.xiaowine.ui.Tools.getProp
import cn.xiaowine.ui.appcompat.HyperButton

class WineDialog(context: Context) : Dialog(context, R.style.Theme_WineDialog) {
    val titleView: AppCompatTextView
        get() = findViewById(R.id.title_view)
    val messageView: AppCompatTextView
        get() = findViewById(R.id.message_view)
    val customizAreaeView: LinearLayout
        get() = findViewById(R.id.customize_area_view)
    val buttonAreaView: LinearLayout
        get() = findViewById(R.id.button_area_view)
    val constraintLayout: ConstraintLayout
        get() = findViewById(R.id.constraintLayout)

    init {
        setContentView(R.layout.wine_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConstraintSet().apply {
            clone(constraintLayout)
            val dp20toPx = dp2px(context, 20f)
            connect(R.id.title_view, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, dp20toPx)
            connect(R.id.title_view, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20toPx)
            connect(R.id.title_view, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20toPx)
            connect(R.id.message_view, ConstraintSet.TOP, R.id.title_view, ConstraintSet.BOTTOM, dp2px(context, 12f))
            connect(R.id.message_view, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20toPx)
            connect(R.id.message_view, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20toPx)
            connect(R.id.button_area_view, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dp20toPx)
            connect(R.id.button_area_view, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dp20toPx)
            connect(R.id.button_area_view, ConstraintSet.TOP, R.id.customize_area_view, ConstraintSet.TOP, dp2px(context, 14f))
            connect(R.id.button_area_view, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, dp20toPx)
            applyTo(constraintLayout)
        }
        titleView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 19f)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                paint.typeface = Typeface.create(null, 500, false)
            }
        }
        messageView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
        }
        window?.apply {
            if (getProp("ro.build.characteristics") == "tablet" || context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setGravity(Gravity.CENTER)
            } else {
                setGravity(Gravity.BOTTOM)
                attributes.y = dp2px(context, 15f)
            }
            setBackgroundDrawable(createRoundShape(dp2px(context, 30f).toFloat(), ContextCompat.getColor(context, R.color.dialog_background_color)))
            attributes.apply {
                dimAmount = 0.5F
                width = dp2px(context, 380f)
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

    fun addButton(string: String, event: (() -> Unit)? = null): HyperButton {
        return HyperButton(context).apply {
            (layoutParams as LinearLayout.LayoutParams).apply {
                setMargins(dp2px(context, 6f), dp2px(context, 10f), dp2px(context, 6f), dp2px(context, 6f))
            }
            text = string
            buttonAreaView.addView(this)
            setOnClickListener {
                if (!it.isEnabled) return@setOnClickListener
                if (event != null) {
                    event()
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

    fun setMessage(message: String) {
        messageView.apply {
            text = message
            visibility = if (message.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    fun addCustomizeView(customizeView: View) {
        customizAreaeView.addView(customizeView)
    }
}