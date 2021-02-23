package com.mrtdev.quoteslove.base.extensions

import androidx.annotation.DrawableRes
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.domain.quotes.models.QuotesAvatar

val QuotesAvatar.drawableRes: Int
    @DrawableRes get() = when (this) {
        QuotesAvatar.HEART-> R.drawable.heart_1
        QuotesAvatar.FIRE_HEART -> R.drawable.fire_heart
        QuotesAvatar.HEART_BORDER -> R.drawable.heart_border
        QuotesAvatar.HEART_PULSE -> R.drawable.heart_pulse
        QuotesAvatar.HEART_PULSE_BLUE -> R.drawable.heart_pulse_blue
        QuotesAvatar.HEART_PUZZLE -> R.drawable.heart_puzzle
        QuotesAvatar.MELTING_HEART -> R.drawable.melting_heart
        QuotesAvatar.SKULL_HEART -> R.drawable.skull_heart
        QuotesAvatar.ENVELOPE_LOVE -> R.drawable.envelope_love
        QuotesAvatar.FAVORITE_FOLDER -> R.drawable.favorite_folder
        QuotesAvatar.HALF_HEART -> R.drawable.half_heart
        QuotesAvatar.HEART_LOCK -> R.drawable.heart_lock
        QuotesAvatar.HEART_OUTLINE -> R.drawable.heart_outline
        QuotesAvatar.LOVE_ARROW -> R.drawable.love_arrow
        QuotesAvatar.LOVE_MESSAGE -> R.drawable.love_message
        QuotesAvatar.LOVE_POTION -> R.drawable.love_potion
        QuotesAvatar.PHILTRE_LOVE -> R.drawable.philtre_love
        QuotesAvatar.ROMANCE -> R.drawable.romance
        QuotesAvatar.SKULL_HEART_LOVE -> R.drawable.skull_heart_love
        QuotesAvatar.WEDDING_CAKE -> R.drawable.wedding_cake
        QuotesAvatar.WEDDING_DAY -> R.drawable.wedding_day
        QuotesAvatar.WEDDING_GIFT -> R.drawable.wedding_gift
    }