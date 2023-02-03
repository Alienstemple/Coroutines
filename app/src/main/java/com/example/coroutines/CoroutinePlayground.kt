package com.example.coroutines

import kotlinx.coroutines.*
import org.junit.Test

@ExperimentalStdlibApi
class CoroutinePlayground {
    @Test
    fun main() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
            println("Handle $exception in CoroutineExceptionHandler")
        }

        // handler в scope работает
        val topLevelScope = CoroutineScope(Job() + coroutineExceptionHandler)

        topLevelScope.launch {
            launch {
                throw RuntimeException("RuntimeException in nested coroutine")
            }
        }

        Thread.sleep(100)
    }

    @Test
    fun testAwaitTryCatch() {

        val topLevelScope = CoroutineScope(SupervisorJob())

        val deferredResult = topLevelScope.async {
            throw RuntimeException("RuntimeException in async coroutine")
        }

        topLevelScope.launch {
            try {
                deferredResult.await()
            } catch (exception: Exception) {
                println("Handle $exception in try/catch")
            }
        }

        Thread.sleep(100)
    }

    @Test
    fun main2() {
        println("Hello")
        val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Exception thrown in one of the children. $exception")
        }

        val scope = CoroutineScope(Dispatchers.Unconfined).launch(/*SupervisorJob()*/) {
            supervisorScope {
                // SupervisorJob - не кидаем исключ, но cancel все job-ы
                // Job A
                val jobA = launch() {
                    val resultA = getResult(1)
                    println("Result A = $resultA")
                }
//            jobA.invokeOnCompletion { throwable ->
//                if (throwable != null) {
//                    println("Error getting result A: $throwable")
//                }
//            }
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
        }

        Thread.sleep(2000)
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
}
