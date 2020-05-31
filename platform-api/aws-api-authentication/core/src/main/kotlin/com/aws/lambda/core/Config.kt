package com.aws.lambda.core

import ws.osiris.aws.ApplicationConfig
import ws.osiris.aws.Stage
import java.time.Duration

/**
 * Configuration that controls how the application is deployed to AWS.
 */
val config = ApplicationConfig(
    applicationName = "aws-api-authentication",
    lambdaMemorySizeMb = 512,
    lambdaTimeout = Duration.ofSeconds(10),
    environmentVariables = mapOf("ENVIRONMENT_VARIABLE" to "Bobaaa"),
    stages = listOf(
        Stage(
            name = "dev",
            description = "Development stage",
            deployOnUpdate = true,
            variables = mapOf("VAR" to "dev_value")
        ),
        Stage(
            name = "test",
            description = "Test stage",
            deployOnUpdate = true,
            variables = mapOf("VAR" to "test_value")
        )
    )
)
