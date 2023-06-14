package faketwitter.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface PostState{
    object Loading : PostState

    class Sucsess(val posts: GetPost): PostState
    class  Erorr(val message : String): PostState

}
class PostsViewModule(application: Application) : AndroidViewModel(application) {
    val liveData = MutableLiveData<PostState>(PostState.Loading)

    val postSeris = RetrofitFactory.postService

    fun fetch(){
        liveData.value = PostState.Loading

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = postSeris.getPost().execute()
                viewModelScope.launch(Dispatchers.Main){
                    if (response.isSuccessful){
                        val reqest = response.body()
                        liveData.value = reqest?.let { PostState.Sucsess(it) }
                    }
                    else{
                        liveData.value = PostState.Erorr(response.message())
                    }
                }
            }
            catch (e: Exception){
                launch(Dispatchers.Main) {
                    liveData.value = PostState.Erorr(e.message ?: "Erorr null")
                }
            }
        }
    }
}