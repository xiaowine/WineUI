package cn.xiaowine.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.databinding.WineDialogProgressbarBinding
import cn.xiaowine.ui.tools.DrawableTools.createRoundShape
import cn.xiaowine.ui.tools.Tools
import cn.xiaowine.ui.tools.Tools.dp

class WineWaitDialog(context: Context) : Dialog(context, R.style.Theme_WineDialog) {

    private var _binding: WineDialogProgressbarBinding? = null
    private val binding: WineDialogProgressbarBinding get() = _binding!!
    val titleView: AppCompatTextView
        get() = binding.titleView

    val progressBar: ProgressBar
        get() = binding.progressBar

    init {
        setCancelable(false)
        _binding = WineDialogProgressbarBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.apply {
            if (Tools.getProp("ro.build.characteristics") == "tablet" || context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setGravity(Gravity.CENTER)
                setWindowAnimations(R.style.Theme_WineDialogPadAnim)
            } else {
                setGravity(Gravity.BOTTOM)
                attributes.y = 15.dp
                setWindowAnimations(R.style.Theme_WineDialogAnim)
            }
            setBackgroundDrawable(createRoundShape(30f.dp, ContextCompat.getColor(context, R.color.dialog_background_color)))
            attributes.apply {
                dimAmount = 0.5F
                width = 380.dp
                height = 78.dp
            }
        }
        titleView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
            setPadding(10.dp, 0, 0, 0)
        }
        progressBar.apply {
            layoutParams.apply {
                width = 25.dp
                height = 25.dp
            }
            val rotateDrawable = RotateDrawable().apply {
                drawable = ContextCompat.getDrawable(context, R.drawable.progress_icon)
                fromDegrees = 0f
                toDegrees = 1080f
                pivotX = 0.5f
                pivotY = 0.5f
            }
            indeterminateDrawable = LayerDrawable(arrayOf(rotateDrawable))
        }
        binding.root.apply {
            setPadding(32.dp, 0, 0, 0)
        }
    }

    fun setTitle(title: String) {
        titleView.text = title
    }

    override fun setTitle(@StringRes title: Int) {
        titleView.setText(title)
    }
}