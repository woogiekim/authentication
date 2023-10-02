package org.auth

import java.util.*

interface SecretKeyGenerator {

    fun generate(): String
}

class DefaultSecretKeyGenerator : SecretKeyGenerator {

    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
