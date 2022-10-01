package com.example.processor

import java.io.OutputStreamWriter

object Utils {

    fun OutputStreamWriter.writeLine(str: String) {
        this.write("$str\n")
    }

}