package com.example.coroutines

import kotlinx.coroutines.*

fun main() {
    println("Hello")
    val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown in one of the children. $exception")
    }

    runBlocking {
        val parentJob = launch(handler) {   // SupervisorJob - не кидаем исключ, но cancel все job-ы
            // Job A
            val jobA = launch() {
                val resultA = getResult(1)
                println("Result A = $resultA")
            }
            jobA.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    println("Error getting result A: $throwable")
                }
            }
            // Job B
            val jobB = launch(/*SupervisorJob()*/) {
                val resultB = getResult(2)
                println("Result B = $resultB")
            }
            jobB.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    println("Error getting result B: $throwable")
                }
            }
            // Job C
            val jobC = launch() {
                val resultC = getResult(3)
                println("Result C = $resultC")
            }
            jobC.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    println("Error getting result C: $throwable")
                }
            }
        }
        parentJob.invokeOnCompletion { throwable ->
            if (throwable != null)
                println("Parent finished with error: $throwable")
            else
                println("Parent SUCCESS")
        }
    }
}

suspend fun getResult(int: Int): Int {
    delay(int * 500L)
    if (int == 2) {
        throw Exception("Exception in number = $int")
    }
    return int * 2
}

suspend fun getData() {

}
