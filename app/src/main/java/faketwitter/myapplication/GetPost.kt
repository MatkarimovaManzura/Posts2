package faketwitter.myapplication

data class Post(
    val id: Int,
    val content: String,
    val author: Author,
    val comments: Int,
    val likes: Int,
    val shares: Int
)

data class Author(
    val avatar: String,
    val name: String
)

data class GetPost(
    val message: Any,
    val posts: List<Post>
)

class SetPost(
    val author: String,
    val content: String
)

class PostResponse(
    val message: Any,
    val posts: Post
)