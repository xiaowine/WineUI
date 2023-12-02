package cn.xiaowine.ui.appcompat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

@SuppressLint("ViewConstructor")
class WineRoundCornerImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var paint: Paint? = null
    private var paint2: Paint? = null
    val roundWidth: Int = 25
    val roundHeight: Int = 25

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        paint = Paint()
        paint!!.color = Color.WHITE
        paint!!.isAntiAlias = true
        paint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        paint2 = Paint()
        paint2!!.xfermode = null
    }

    override fun draw(canvas: Canvas) {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas2 = Canvas(bitmap)
        super.draw(canvas2)
        drawLiftUp(canvas2)
        drawRightUp(canvas2)
        drawLiftDown(canvas2)
        drawRightDown(canvas2)
        canvas.drawBitmap(bitmap, 0f, 0f, paint2)
        bitmap.recycle()
    }

    private fun drawLiftUp(canvas: Canvas) {
        canvas.drawPath(Path().apply {
            moveTo(0f, roundHeight.toFloat())
            lineTo(0f, 0f)
            lineTo(roundWidth.toFloat(), 0f)
            arcTo(
                RectF(
                    0f,
                    0f,
                    (roundWidth * 2).toFloat(),
                    (roundHeight * 2).toFloat()
                ), -90f, -90f
            )
            close()
        }, paint!!)
    }

    private fun drawLiftDown(canvas: Canvas) {
        canvas.drawPath(Path().apply {
            moveTo(0f, (height - roundHeight).toFloat())
            lineTo(0f, height.toFloat())
            lineTo(roundWidth.toFloat(), height.toFloat())
            arcTo(
                RectF(
                    0f,
                    (height - roundHeight * 2).toFloat(),
                    (0 + roundWidth * 2).toFloat(),
                    height.toFloat()
                ), 90f, 90f
            )
            close()
        }, paint!!)
    }

    private fun drawRightDown(canvas: Canvas) {
        canvas.drawPath(Path().apply {
            moveTo((width - roundWidth).toFloat(), height.toFloat())
            lineTo(width.toFloat(), height.toFloat())
            lineTo(width.toFloat(), (height - roundHeight).toFloat())
            arcTo(
                RectF(
                    (width - roundWidth * 2).toFloat(),
                    (height - roundHeight * 2).toFloat(),
                    width.toFloat(),
                    height.toFloat()
                ), 0f, 90f
            )
            close()
        }, paint!!)
    }

    private fun drawRightUp(canvas: Canvas) {
        canvas.drawPath(Path().apply {
            moveTo(width.toFloat(), roundHeight.toFloat())
            lineTo(width.toFloat(), 0f)
            lineTo((width - roundWidth).toFloat(), 0f)
            arcTo(
                RectF(
                    (width - roundWidth * 2).toFloat(),
                    0f,
                    width.toFloat(),
                    (0 + roundHeight * 2).toFloat()
                ), -90f, 90f
            )
            close()
        }, paint!!)
    }
}