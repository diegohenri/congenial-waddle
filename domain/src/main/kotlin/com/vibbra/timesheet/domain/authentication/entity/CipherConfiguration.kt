package com.vibbra.timesheet.domain.authentication.entity

data class CipherConfiguration(
    var encryptionKey: String,
    var algorithm: String,
    var operationMode: String,
    var paddingScheme: String,
    var cipherTransformation: String
) {
    constructor(
        encryptionKey: String,
        algorithm: String,
        operationMode: String,
        paddingScheme: String
    ) : this(
        encryptionKey = encryptionKey,
        algorithm = algorithm,
        operationMode = operationMode,
        paddingScheme = paddingScheme,
        cipherTransformation = "${algorithm}/${operationMode}/${paddingScheme}")
}