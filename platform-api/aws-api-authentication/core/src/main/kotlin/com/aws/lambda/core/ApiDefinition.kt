package com.aws.lambda.core

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.google.gson.Gson
import ws.osiris.core.ComponentsProvider
import ws.osiris.core.api
import java.util.*

const val ENVIRONMENT_VARIABLE = "ENVIRONMENT_VARIABLE" // Set on Config.kt
const val SESSION_TIME_OUT = 300L

/** The API. */
val api = api<CreatedComponents> {
    // Index url
    get("/") {
        mapOf("message" to "Index page!")
    }

    get("/getPassword") { req ->
        val password = req.queryParams["password"]
        mapOf("password" to password, "jwtEncrypt" to jwtEncryption.encrypt(password))
    }

    // Add User, params: username=admin&password=admin&userRole=admin
    post("/addUser") { req ->
        val username = req.queryParams["username"]
        val password = req.queryParams["password"]
        val userRole = req.queryParams["userRole"]
        val user = UserModel(
                username = username,
                password = jwtEncryption.encrypt(password),
                userRole = userRole
        )
        dynamoDBMapper.save(user)
        mapOf("user" to user)
    }

    // Get all Users
    get("/getAllUsers") {
        val users: List<UserModel?> = dynamoDBMapper.scan(UserModel::class.java, DynamoDBScanExpression())
        mapOf("users" to users)
    }

    // Get User Token, body: "{"username":"user", "password":"user"}"
    post("/authentication") { req ->
        val body = req.body
        val userModelBody = Gson().fromJson(body.toString(), UserModel::class.java)
        val username = userModelBody.username
        val password = userModelBody.password
        var token: TokenModel? = null
        if (username != null && password != null) {
            val user = dynamoDBMapper.load(UserModel::class.java, username, jwtEncryption.encrypt(password))
            if (user != null) {
                token = TokenModel(user.userRole, UUID.randomUUID().toString(), SESSION_TIME_OUT)
            }
        }
        mapOf("token" to token)
    }

    // Enable cors for non-simple POST /authentication
    options("/authentication") { req ->
        req.responseBuilder()
                .header("Access-Control-Allow-Methods", "*")
                .header("Access-Control-Allow-Headers", "*")
                .header("Access-Control-Allow-Origin", "*")
                .build()
    }
    
}

/**
 * Creates the components used by the example API.
 */
fun createComponents(): CreatedComponents = CreatedComponentsImpl()

/**
 * A Component interface
 */
interface CreatedComponents : ComponentsProvider {
    val environmentVariable: String
    val dynamoDBMapper: DynamoDBMapper
    val jwtEncryption: JwtEncryption
}

/**
 * A Component implementation
 */
class CreatedComponentsImpl : CreatedComponents {
    override val environmentVariable: String = System.getenv(ENVIRONMENT_VARIABLE) ?: "[Environment variable not set]"
    override val dynamoDBMapper: DynamoDBMapper = getDynamoDBMapper()
    override var jwtEncryption: JwtEncryption = JwtEncryption()
}
