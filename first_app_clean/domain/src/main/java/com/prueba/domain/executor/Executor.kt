package com.prueba.domain.executor

import io.reactivex.Scheduler

/**
 * Executor
 */
interface Executor {
    fun new(): Scheduler

    fun main(): Scheduler
}
