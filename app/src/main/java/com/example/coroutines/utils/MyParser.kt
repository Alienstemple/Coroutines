package com.example.coroutines.utils

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream

class MyParser<T>(private val clazz: Class<T>) {

    private val objectMapper = ObjectMapper()

    fun parse(input: String) = parse(input, clazz)

    fun <T> parse(input: String, clazz: Class<T>): T {
        return objectMapper.readValue(input, clazz)
    }

    fun parseFile(input: String) = parseFile(input, clazz)

    fun <T> parseFile(input: String, clazz: Class<T>): T {
        val file = loadFile(input) ?: throw IllegalStateException("Except while reading file") // TODO illegal state ex
        return objectMapper.readValue(file, clazz)
    }

    fun loadFile(file: String): InputStream? { // TODO ? nullable
        return javaClass.classLoader?.getResourceAsStream(file)
    }
}