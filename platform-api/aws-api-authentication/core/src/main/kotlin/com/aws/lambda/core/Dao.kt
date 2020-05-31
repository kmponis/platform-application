package com.aws.lambda.core

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.amazonaws.services.dynamodbv2.model.BillingMode
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import java.util.concurrent.TimeUnit

private val dynamoClient: AmazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient()
private val dynamoDBMapper = DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient())
private var jwtTableCreated = false
private var userTableCreated = false

/**
 * Get dynamoDBMapper and initialise Tables
 */
fun getDynamoDBMapper(): DynamoDBMapper {
    if (!jwtTableCreated) {
        createJwtTable()
    }
    if (!userTableCreated) {
        createUserTable()
    }
    return dynamoDBMapper
}

/**
 * Create Jwt table on the fly
 */
fun createJwtTable() {
    try {
        dynamoClient.createTable(
                dynamoDBMapper.generateCreateTableRequest(JwtModel::class.java)
                        .withBillingMode(BillingMode.PROVISIONED)
                        .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))
        )
        // Wait for Table creation
        TimeUnit.SECONDS.sleep(5L)
    } catch (e: ResourceInUseException) {
        // TODO: Print a log message on API gateway
        println("Table 'Jwt' already created.")
    } finally {
        jwtTableCreated = true
    }
}

/**
 * Create User table on the fly
 */
fun createUserTable() {
    try {
        dynamoClient.createTable(
                dynamoDBMapper.generateCreateTableRequest(UserModel::class.java)
                        .withBillingMode(BillingMode.PROVISIONED)
                        .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))
        )
        // Wait for Table creation
        TimeUnit.SECONDS.sleep(5L)
    } catch (e: ResourceInUseException) {
        // TODO: Print a log message on API gateway
        println("Table 'User' already created.")
    } finally {
        userTableCreated = true
    }
}

/**
 * Data class JwtModel
 * 
 * TODO Add environment_prefix before tableName to support multiple environmnents
 * Add variable to Config and use it by 'System.getenv(ENVIRONMENT_VARIABLE) ?: "Dev_"'
 */
@DynamoDBTable(tableName = "Jwt")
data class JwtModel(
        @DynamoDBHashKey(attributeName="name")
        var name: String = "",

        @DynamoDBAttribute(attributeName="jws_key")
        var jwsKey: String = ""
)

/**
 * Data class UserModel
 * 
 * TODO Add environment_prefix before tableName to support multiple environmnents
 * Add variable to Config and use it by 'System.getenv(ENVIRONMENT_VARIABLE) ?: "Dev_"'
 */
@DynamoDBTable(tableName = "User")
data class UserModel(
        @DynamoDBHashKey(attributeName="username")
        var username: String? = null,

        @DynamoDBRangeKey(attributeName = "password")
        var password: String? = null,

        @DynamoDBAttribute(attributeName = "user_role")
        var userRole: String? = null
)

/**
 * Data class TokenModel
 */
data class TokenModel(var userRole: String? = null, var accessToken: String? = null, var sessionTimeOut: Long? = null)