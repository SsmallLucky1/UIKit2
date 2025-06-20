package com.ssmalllucky.android.ui2.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import androidx.fragment.app.FragmentActivity

/**
 * 显示工具类，用于 dp、px、sp 的相互转换，以及屏幕宽高、像素密度的获取
 * @author: ssmalllucky
 * @since: 2025/6/20
 */
object DisplayUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        // 加 0.5f 是为了在 int 强转时实现四舍五入的效果，避免造成向下误差舍入，导致 UI 边界模糊、控件尺寸偏差等问题
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     * 54.
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获取手机屏幕的宽度
     * @param activity  当前界面活动
     * <br />
     * 当 activity 为 null、处于 isFinishing 状态、处于 isDestroyed 状态时，返回 0
     */
    fun getPhoneWidth(activity: FragmentActivity?): Int {
        if (activity == null || activity.isFinishing || activity.isDestroyed) {
            return 0
        }

        val displayMetrics = DisplayMetrics()


        if (Build.VERSION.SDK_INT >= 30) {
            return activity.windowManager.currentWindowMetrics.bounds.width()
        } else {
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }
    }

    /**
     * 获取手机屏幕的高度
     * @param activity  当前界面活动
     * <br />
     * 当 activity 为 null、处于 isFinishing 状态、处于 isDestroyed 状态时，返回 0
     */
    fun getPhoneHeight(activity: FragmentActivity?): Int {
        if (activity == null || activity.isFinishing || activity.isDestroyed) {
            return 0
        }

        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= 30) {
            return activity.windowManager.currentWindowMetrics.bounds.height()
        } else {
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }
    }


    /**
     * 检查 Context，如果为 null，则返回 false，否则返回 true
     * @param context 上下文
     */
    private fun checkContext(context: Context?): Boolean {
        return context != null
    }

    /**
     * 获取屏幕密度
     * @param context 上下文
     * @return 若 checkContext(context) 为 false，则返回 0f
     */
    fun getDensity(context: Context): Float {
        if (!checkContext(context)) {
            return 0f
        }
        return context.resources.displayMetrics.density
    }
}