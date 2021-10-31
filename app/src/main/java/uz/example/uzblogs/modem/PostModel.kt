package uz.example.uzblogs.modem

data class PostModel(
    val id: String,
    val owner: UserModel,
    val text: String,
    val image: String,
    val publishDate: String
)