package com.fmdwallpaper.myapplication

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class HomeDirections private constructor() {
  private data class ActionHomeToDetail(
    public val wallpaperimage: String = "null"
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_home_to_detail

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("wallpaperimage", this.wallpaperimage)
      return result
    }
  }

  public companion object {
    public fun actionHomeToRegister(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_register)

    public fun actionHomeToDetail(wallpaperimage: String = "null"): NavDirections =
        ActionHomeToDetail(wallpaperimage)
  }
}
