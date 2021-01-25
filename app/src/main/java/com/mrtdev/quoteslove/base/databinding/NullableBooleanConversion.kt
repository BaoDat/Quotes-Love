package com.mrtdev.quoteslove.base.databinding

import androidx.databinding.InverseMethod

class NullableBooleanConversion private constructor() {
    companion object {
        @JvmStatic
        @InverseMethod("safeBox")
        fun safeUnbox(boxed: Boolean?): Boolean = boxed ?: false

        @JvmStatic
        fun safeBox(unboxed: Boolean): Boolean? =
            unboxed
    }
}
