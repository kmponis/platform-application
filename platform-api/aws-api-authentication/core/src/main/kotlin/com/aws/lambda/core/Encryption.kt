package com.aws.lambda.core

import com.amazonaws.encryptionsdk.AwsCrypto
import com.amazonaws.encryptionsdk.CryptoResult
import com.amazonaws.encryptionsdk.kms.KmsMasterKey
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*

val jwtKey: Key = JwtEncryption().getJwtKey()
val jws = "jws"

class JwtEncryption {
    fun encrypt(encryptionText: String): String {
        // TODO replace claim subject with a custom one
        return Jwts.builder().setSubject(encryptionText).signWith(jwtKey).compact()
    }

    // This is ONLY used in EncryptTest
    fun decrypt(decryptionText: String): String? {
        return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(decryptionText).body.subject
    }

    fun getJwtKey(): Key {
        val keyString: String = try {
            val jwt: JwtModel = getDynamoDBMapper().load(JwtModel::class.java, jws)
            AwsKmsEncryption().decrypt(jwt.jwsKey)
        } catch (ise: IllegalStateException) {
            println("Value doesn't exists in DB, create a new key")
            createNewKey()
        }
        return Keys.hmacShaKeyFor(keyString.toByteArray())
    }

    private fun createNewKey(): String {
        val keyString: String = UUID.randomUUID().toString()
        getDynamoDBMapper().save(JwtModel(name = jws, jwsKey = AwsKmsEncryption().encrypt(keyString)))
        return keyString
    }
}

class AwsKmsEncryption {
    private val awsCrypto = AwsCrypto()
    private val kmsArn = "arn:aws:kms:eu-west-1:709954733971:key/a6f079a6-53fb-4c90-9f00-915d580d0d07"
    private val kmsMasterKeyProvider = KmsMasterKeyProvider(kmsArn)
    private val contextMap = mapOf("name" to "string")

    fun encrypt(encryptionText: String): String {
        return awsCrypto.encryptString(kmsMasterKeyProvider, encryptionText, contextMap).result
    }

    fun decrypt(decryptionText: String): String {
        val decryptResult: CryptoResult<String, KmsMasterKey> =
                awsCrypto.decryptString(kmsMasterKeyProvider, decryptionText)

        // Check the encryption context (and ideally the master key) to
        // ensure this is the expected cipherText
        check(decryptResult.masterKeyIds[0] == kmsArn) { "Wrong key id!" }
        // The SDK may add information to the encryption context, so check to
        // ensure all of the values are present
        for ((key, value) in contextMap) {
            check(value == decryptResult.encryptionContext[key]) { "Wrong Encryption Context!" }
        }

        return decryptResult.result
    }
}


