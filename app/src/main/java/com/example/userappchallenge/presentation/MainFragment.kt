package com.example.userappchallenge.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.userappchallenge.databinding.MainFragmentBinding
import com.example.userappchallenge.presentation.MainViewViewState.DataReadyToShow
import com.example.userappchallenge.presentation.MainViewViewState.LoadingData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(
        MainFragmentBinding.inflate(layoutInflater, container, false)
    ) {
        binding = this
        binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.fetchUser()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is DataReadyToShow -> {
                    showLoader(false)
                    adapter.updateUserData(viewState.viewData)
                }
                is LoadingData -> showLoader(true)
            }
        }
    }

    private fun setUpView() {
        binding.userListRecyclerView.adapter = adapter
    }

    private fun showLoader(isVisible: Boolean) {
        binding.progress.apply {
            visibility = if (isVisible) VISIBLE else GONE
        }
    }
}