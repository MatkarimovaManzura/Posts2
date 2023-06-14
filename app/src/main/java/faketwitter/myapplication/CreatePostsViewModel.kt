package faketwitter.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface AddPosts{
    object Loading: AddPosts
    object Success: AddPosts
    class Error(val message: String): AddPosts
}
class CreatePostsViewModel(application: Application) : AndroidViewModel(application){

    val liveData = MutableLiveData<AddPosts>(AddPosts.Loading)

    private val postService = RetrofitFactory.postService

    fun load(post: SetPost){
        liveData.value = AddPosts.Loading


        viewModelScope.launch(Dispatchers.IO){

            val response = postService.setPost(post).execute()
            viewModelScope.launch(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        liveData.value = AddPosts.Success
                    }
                    else{
                        liveData.value = AddPosts.Error(response.message() ?: "Error")
                    }
                } catch (e: Exception) {

                    liveData.value = AddPosts.Error(e.message.toString())
                }
            }


        }
    }
}