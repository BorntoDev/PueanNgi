package com.borntodev.trithep.pueanterngijakaira

import android.content.Context
import java.lang.reflect.AccessibleObject.setAccessible
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.util.Log


object TypefaceUtil {

    /**
     * Using reflection to override default typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
     * @param context to work with assets
     * @param defaultFontNameToOverride for example "monospace"
     * @param customFontFileNameInAssets file name of the fonts from assets
     */
    fun overrideFont(context: Context, defaultFontNameToOverride: String, customFontFileNameInAssets: String) {
//        val customFontTypeface = Typeface.createFromAsset(context.assets, customFontFileNameInAssets)
        var typeFace: Typeface? = ResourcesCompat.getFont(context, R.font.mitr_regular)
        val defaultFontTypefaceField = Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
        defaultFontTypefaceField.isAccessible = true
        defaultFontTypefaceField.set(null, typeFace)

//        try {
//            val customFontTypeface = Typeface.createFromAsset(context.assets, customFontFileNameInAssets)
//
//            val defaultFontTypefaceField = Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
//            defaultFontTypefaceField.isAccessible = true
//            defaultFontTypefaceField.set(null, customFontTypeface)
//        } catch (e: Exception) {
//            Log.e("ERROR FONT:","Can not set custom fonts $customFontFileNameInAssets instead of $defaultFontNameToOverride")
//        }

    }
}