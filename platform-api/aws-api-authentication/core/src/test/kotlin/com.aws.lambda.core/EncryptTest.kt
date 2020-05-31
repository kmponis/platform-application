package com.aws.lambda.core

import kotlin.test.assertEquals
import org.junit.Test

/**
 * TODO 
 * - Mock the AwsKmsEncryption call (Mock getDynamoDBMapper)
 * - Amend jws to jwsTest (No need to change DB values)
 * - Add more cases e.x.: 
 *      whenNotDBInitialised_thenEncrypt
 *      whenNotDBInitialised_thenDecrypt
 *      whenDBInitialised_thenGetJwtKey
 *      whenNotDBInitialised_thenGetJwtKey
 */
class JwtEncryptionTest {
    private val jwtEncryption: JwtEncryption = JwtEncryption()
    private val jwtEncryptionText = "hola"
    private val jwtEncryptedText = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhIn0.llximUMgDX83UR5oH9ZdXfbIIO3QGx1UqZDpxmWGZLU"

    @Test fun whenDBInitialised_thenEncrypt() {
        assertEquals(jwtEncryptedText, jwtEncryption.encrypt(jwtEncryptionText))
    }

    @Test fun whenDBInitialised_thenDecrypt() {
        assertEquals(jwtEncryptionText, jwtEncryption.decrypt(jwtEncryptedText))
    }
}

class AwsKmsEncryptionTest {
    private val awsKmsEncryption: AwsKmsEncryption = AwsKmsEncryption()
    private val awsKmsEncryptionText = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhIn0.llximUMgDX83UR5oH9ZdXfbIIO3QGx1UqZDpxmWGZLU"

    @Test fun whenAwsKmsEncrypt_thenAwsKmsDecrypt() {
        val awsKmsEncryptedText = awsKmsEncryption.encrypt(awsKmsEncryptionText)
        val awsKmsDecryptedText = awsKmsEncryption.decrypt(awsKmsEncryptedText)
        assertEquals(awsKmsEncryptionText, awsKmsDecryptedText)
    }
}
