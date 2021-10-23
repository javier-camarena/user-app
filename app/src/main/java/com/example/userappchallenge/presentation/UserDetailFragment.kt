package com.example.userappchallenge.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.userappchallenge.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.security.InvalidParameterException


@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    companion object {
        fun newInstance(userId: String) = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putString("userId", userId)
            }
        }
    }

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val userId = arguments?.getString("userId")
                ?: throw InvalidParameterException("userIs is required")
            viewModel.fetchUserById(userId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(
        FragmentUserDetailBinding.inflate(layoutInflater, container, false)
    ) {
        binding = this
        binding.root
    }
}