package faketwitter.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import faketwitter.myapplication.databinding.FragmentPostsBinding

class PostsFragment : Fragment(R.layout.fragment_posts) {
    lateinit var binding: FragmentPostsBinding
    val viewModel: PostsViewModule by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPostsBinding.bind(view)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        binding.toCreate.setOnClickListener {
        findNavController().navigate(R.id.from_posts_to_create)
        }

        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            when (state){
                is PostState.Sucsess -> {
                    binding.recycler.adapter =PostAdapter(state.posts)
                }
                is PostState.Erorr -> {
                    Toast.makeText(requireContext(), "Error : ${state.message}", Toast.LENGTH_SHORT)
                }
                is PostState.Loading -> {}
            }
        }

        viewModel.fetch()
    }
}