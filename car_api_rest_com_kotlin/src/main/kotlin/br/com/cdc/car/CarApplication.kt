package br.com.cdc.car

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile

@SpringBootApplication
class CarApplication
fun main(args: Array<String>) {
	runApplication<CarApplication>(*args)
}
