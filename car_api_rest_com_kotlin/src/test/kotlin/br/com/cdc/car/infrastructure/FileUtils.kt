package br.com.cdc.car.infrastructure

import org.springframework.core.io.ClassPathResource

fun loadFileContent(fileName: String): String {
    return ClassPathResource(fileName)
        .inputStream.readAllBytes()
        .toString(Charsets.UTF_8)
}