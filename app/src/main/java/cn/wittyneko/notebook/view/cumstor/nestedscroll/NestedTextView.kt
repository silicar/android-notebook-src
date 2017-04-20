package cn.wittyneko.notebook.view.cumstor.nestedscroll

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import android.support.v4.view.ViewCompat.dispatchNestedPreScroll
import android.view.VelocityTracker
import android.view.ViewConfiguration


/**
 * Created by wittyneko on 17-4-19.
 */

class NestedTextView : TextView, NestedScrollingChild {
    lateinit var mHelp: NestedScrollingChildHelper
    private var mVelocityTracker: VelocityTracker? = null
    private var ox: Float = 0.toFloat()
    private var oy: Float = 0.toFloat()
    private val consumed = IntArray(2)
    private val offsetInWindow = IntArray(2)

    private var mTouchSlop: Int = 0
    private var mMinFlingVelocity: Int
    private var mMaxFlingVelocity: Int

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        mHelp = NestedScrollingChildHelper(this)
        isNestedScrollingEnabled = true
        val vc = ViewConfiguration.get(context)
        mTouchSlop = vc.scaledTouchSlop
        mMinFlingVelocity = vc.scaledMinimumFlingVelocity
        mMaxFlingVelocity = vc.scaledMaximumFlingVelocity
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        mHelp = NestedScrollingChildHelper(this)
        isNestedScrollingEnabled = true
        val vc = ViewConfiguration.get(context)
        mTouchSlop = vc.scaledTouchSlop
        mMinFlingVelocity = vc.scaledMinimumFlingVelocity
        mMaxFlingVelocity = vc.scaledMaximumFlingVelocity
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mHelp.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mHelp.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mHelp.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mHelp.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mHelp.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        return mHelp.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return mHelp.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return mHelp.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mHelp.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        var eventAddedToVelocityTracker = false

        val vtev = MotionEvent.obtain(event)

        when (event!!.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                ox = event!!.getX()
                oy = event.getY()
                //开始滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
            }
            MotionEvent.ACTION_MOVE -> {

                val clampedX = event.getX()

                val clampedY = event.getY()


                var dx: Int = (clampedX - ox).toInt()
                var dy: Int = (clampedY - oy).toInt()

                //分发触屏事件给父类处理
                if (dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)) {
                    //减掉父类消耗的距离
                    dx -= consumed[0]
                    dy -= consumed[1]
                }
                offsetLeftAndRight(dx)
                offsetTopAndBottom(dy)
            }
            MotionEvent.ACTION_UP -> {

                mVelocityTracker!!.addMovement(vtev)
                eventAddedToVelocityTracker = true
                mVelocityTracker!!.computeCurrentVelocity(1000, mMaxFlingVelocity.toFloat())
                val xvel = -VelocityTrackerCompat.getXVelocity(mVelocityTracker, event.getPointerId(0))
                val yvel = -VelocityTrackerCompat.getYVelocity(mVelocityTracker, event.getPointerId(0))
                if (((xvel != 0f || yvel != 0f) )) {
                    dispatchNestedPreFling(xvel, yvel)
                } else {
                    stopNestedScroll()
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker!!.clear()
                }
            }
        }
        if (!eventAddedToVelocityTracker) {
            mVelocityTracker!!.addMovement(vtev)
        }
        vtev.recycle()
        return true
    }
}
