package cn.wittyneko.notebook.view.cumstor.nestedscroll

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.NestedScrollingParent
import android.support.v4.view.NestedScrollingParentHelper
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import cn.wittyneko.utils.LogUtil
import android.support.v4.view.ViewCompat


/**
 * Created by wittyneko on 17-4-19.
 */

class NestedScrollParent : LinearLayout, NestedScrollingParent {
    var mHelp: NestedScrollingParentHelper = NestedScrollingParentHelper(this)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        LogUtil.e("child: ${child.javaClass.simpleName} , target: ${target.javaClass.simpleName} , axes: $nestedScrollAxes")
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL !== 0;
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {

        LogUtil.e("child: ${child.javaClass.simpleName} , target: ${target.javaClass.simpleName} , axes: $nestedScrollAxes")
        mHelp.onNestedScrollAccepted(child, target, nestedScrollAxes)
    }

    override fun onStopNestedScroll(target: View) {
        LogUtil.e("target: ${target.javaClass.simpleName}")
        mHelp.onStopNestedScroll(target)
    }

    override fun getNestedScrollAxes(): Int {
        LogUtil.e("axes： ${mHelp.nestedScrollAxes}")
        return mHelp.nestedScrollAxes
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {

        LogUtil.e("target: ${target.javaClass.simpleName}, dx: $dxConsumed , dy: $dyConsumed, dxUn: $dxUnconsumed , dyUn: $dyUnconsumed ")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        consumed[1] = dy
        LogUtil.e("target: ${target.javaClass.simpleName}, dx: $dx , dy: $dy, dxc: ${consumed[0]} , dyc: ${consumed[1]} ")
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        LogUtil.e("target: ${target.javaClass.simpleName}, velocityX: $velocityX , velocityY: $velocityY , consumed $consumed")
        return false
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        LogUtil.e("target: ${target.javaClass.simpleName}, velocityX: $velocityX , velocityY: $velocityY")
        return false
    }
}
