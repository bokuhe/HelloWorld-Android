package net.sleiv.helloworld.util.timber

import android.util.Log
import timber.log.Timber

class ReportTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < Log.WARN)
            return

        TODO("Reporting")
    }

}