package de.shcreative.pensieve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PensieveApiApplication

fun main(args: Array<String>) {
	runApplication<PensieveApiApplication>(*args)
}
