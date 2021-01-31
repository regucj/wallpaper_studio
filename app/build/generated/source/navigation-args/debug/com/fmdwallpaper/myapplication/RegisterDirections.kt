package com.fmdwallpaper.myapplication

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class RegisterDirections private constructor() {
  public companion object {
    public fun actionRegisterToHome(): NavDirections =
        ActionOnlyNavDirections(R.id.action_register_to_home)
  }
}
