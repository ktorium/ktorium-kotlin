package org.ktorium.kotlin

@InternalKtoriumAPI
public enum class KtoriumVersion {
    Unreleased,
    V0_1_0;

    override fun toString(): String {
        val nameValue: String = this.name
        return if (this == Unreleased) {
            nameValue.lowercase()
        } else {
            nameValue.drop(1).replace('_', '.')
        }
    }
}
