package com.tatak.footballapp.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TestingSchedulerProvider: SchedulerProvider {
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun newThread(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }
}