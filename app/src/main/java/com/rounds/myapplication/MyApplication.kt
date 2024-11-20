package com.rounds.myapplication

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.rounds.myapplication.domain.SampleLoadService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), LifecycleObserver {
    @Inject
    lateinit var sampleLoadService: SampleLoadService

    override fun onCreate() {
        super.onCreate()

        MainScope().launch(Dispatchers.Default) {
            sampleLoadService.loadItemsIfNecessary()
        }
    }
}
