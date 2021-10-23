package com.example.userappchallenge.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.userappchallenge.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.security.InvalidParameterException

/**
 * Secondary view that retrieves user info from repository
 * and show detailed data of the [User]
 */
@AndroidEntryPoint
class UserDetailFragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                UserDetailViewState.Loading -> showLoader(isVisible = true)
                is UserDetailViewState.Ready -> {
                    showData(viewState.viewData)
                }
            }
        }
    }

    private fun showData(viewData: UserDetailViewData) {
        binding.apply {
            userNameTextView.text = viewData.user.fullName
            userNationalityTextView.text = viewData.user.nationality
            userProfileImageView.load(viewData.user.profilePic)
            contactPhoneTextView.text = viewData.phone
            userNickNameTextView.text = viewData.nickName
        }
    }

    private fun showLoader(isVisible: Boolean) {
        binding.progress.apply {
            visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}