package org.ktorium.kotlin.gradle

class KotlinCompilerArgumentsBuilder {
    private val arguments: MutableList<String> = mutableListOf()

    fun add(arg: String) = arguments.add(arg)
    fun requiresOptIn() = arguments.add("-opt-in=kotlin.RequiresOptIn")
    fun requiresJsr305(value: String = "strict") = arguments.add("-Xjsr305=$value")

    fun build(): List<String> = arguments
}
