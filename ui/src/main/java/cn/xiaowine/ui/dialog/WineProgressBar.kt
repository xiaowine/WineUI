package cn.xiaowine.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.xiaowine.ui.R
import cn.xiaowine.ui.Tools
import cn.xiaowine.ui.databinding.WineDialogProgressbarBinding

class WineProgressBar(context: Context) : Dialog(context, R.style.Theme_WineDialog) {

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
                attributes.y = Tools.dp2px(context, 15f)
                setWindowAnimations(R.style.Theme_WineDialogAnim)
            }
            setBackgroundDrawable(Tools.createRoundShape(Tools.dp2px(context, 30f).toFloat(), ContextCompat.getColor(context, R.color.dialog_background_color)))
            attributes.apply {
                dimAmount = 0.5F
                width = Tools.dp2px(context, 380f)
                height = Tools.dp2px(context, 78f)
            }
        }
        titleView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
            setPadding(Tools.dp2px(context, 10f), 0, 0, 0)
        }
        progressBar.apply {
            layoutParams.apply {
                width = Tools.dp2px(context, 25f)
                height = Tools.dp2px(context, 25f)
            }
        }
        binding.root.apply {
            setPadding(Tools.dp2px(context, 32f), 0, 0, 0)
        }
    }

    fun setTitle(title: String) {
        titleView.text = title
    }
    override fun setTitle(title: Int) {
        titleView.setText(title)
    }
}