package faketwitter.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import faketwitter.myapplication.databinding.FragmentCreateBinding

class CreateFragment : Fragment(R.layout.fragment_create) {
    private lateinit var binding: FragmentCreateBinding

    private val viewModel: CreatePostsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateBinding.bind(view)

        binding.toPosts.setOnClickListener {
            findNavController().navigate(R.id.from_create_to_posts)
        }

        binding.add.setOnClickListener {
            if (binding.autor.text.toString().isNotEmpty() && binding.neContent.text.toString().isNotEmpty()){
                val post = SetPost(
                    author = binding.autor.text.toString(),
                    content = binding.neContent.text.toString()
                )
                viewModel.load(post)
            }
            else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.liveData.observe(viewLifecycleOwner){ state ->
            when(state){
                is AddPosts.Success -> {
                    findNavController().navigate(R.id.from_create_to_posts)
                }
                is AddPosts.Error -> {
                    Toast.makeText(requireContext(), "Error : ${state.message}", Toast.LENGTH_SHORT).show()
                }
                is AddPosts.Loading -> {}
            }
        }

    }
}