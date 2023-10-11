package com.stacker4.whopper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhopperApplication

fun main(args: Array<String>) {
	runApplication<WhopperApplication>(*args)
}
