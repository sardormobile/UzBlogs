package uz.example.uzblogs.modem

import java.io.Serializable

data class UserModel(
    val id: String,
    val lastName: String,
    val firstName: String,
    val picture: String
): Serializable