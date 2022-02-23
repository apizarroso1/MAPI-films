package com.example.mapifilms

import java.util.*

enum class Genero {
    TERROR,ACCION,SUSPENSE;

    companion object {
        fun parse(genero: String): Genero {
            return Arrays.stream(values()).filter { p ->
                p.equals(
                    valueOf(genero.uppercase())
                )
            }.findFirst().orElse(TERROR)
        }
    }
}