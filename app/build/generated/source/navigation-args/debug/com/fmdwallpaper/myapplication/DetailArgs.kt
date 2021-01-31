package com.fmdwallpaper.myapplication

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DetailArgs(
  public val wallpaperimage: String = "null"
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("wallpaperimage", this.wallpaperimage)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailArgs {
      bundle.setClassLoader(DetailArgs::class.java.classLoader)
      val __wallpaperimage : String?
      if (bundle.containsKey("wallpaperimage")) {
        __wallpaperimage = bundle.getString("wallpaperimage")
        if (__wallpaperimage == null) {
          throw IllegalArgumentException("Argument \"wallpaperimage\" is marked as non-null but was passed a null value.")
        }
      } else {
        __wallpaperimage = "null"
      }
      return DetailArgs(__wallpaperimage)
    }
  }
}
