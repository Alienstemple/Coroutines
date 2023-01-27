package com.example.coroutines

import kotlinx.coroutines.*

suspend fun main() {
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            val a1 = async {
                delay(1000)
                return@async "one"
            }

            val a2 = async {
                delay(2000)
                return@async "two"
            }

            val res1 = a1.await()
            val res2 = a2.await()
            return@withContext "$res1 $res2"
        }
        println(result)
    }
}