package cz.jannejezchleba.appliftingspacex.ui.navigation

import cz.jannejezchleba.appliftingspacex.R

enum class AppScreens(
    val resourceNameId: Int,
    val resourceIconId: Int? = null
) {
    Splash(R.string.SCREEN_SPLASH),
    NextLaunch(R.string.SCREEN_NEXT_LAUNCH, R.drawable.clock),
    Launches(R.string.SCREEN_LAUNCHES, R.drawable.rocket_launch),
    Rockets(R.string.SCREEN_ROCKETS, R.drawable.rocket),
    RocketDetail(R.string.SCREEN_DETAILS),
    Company(R.string.SCREEN_COMPANY, R.drawable.account_group)
}