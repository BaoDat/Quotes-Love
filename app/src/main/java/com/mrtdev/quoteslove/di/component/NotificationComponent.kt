package com.mrtdev.quoteslove.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface NotificationComponent {

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun context(context: Context)

        abstract fun build(): NotificationComponent
    }
}