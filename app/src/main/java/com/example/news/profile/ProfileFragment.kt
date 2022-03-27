package com.example.news.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import com.example.news.R
import com.example.news.databinding.FragmentProfileBinding
import com.example.news.models.User
import dagger.hilt.android.AndroidEntryPoint
import io.paperdb.Paper


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Paper.init(requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = profileViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Paper.book().read<User>("user")

        binding.userEmail.text = user?.userEmail
        binding.userName.text = user?.userName

        binding.changePassword.setOnClickListener {
            changePassword()
        }
    }


    private fun changePassword() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.change_pass_layout, null)
        val edtOldPass: EditText = view.findViewById(R.id.old_pass)
        val edtConfirmPass: EditText = view.findViewById(R.id.confirm_pass)
        val edtNewPass: EditText = view.findViewById(R.id.new_pass)
        val btnChange : Button = view.findViewById(R.id.btnChangePass)
        
        
        val dialog = Dialog(requireContext(), R.style.Theme_Dialog)

        btnChange.setOnClickListener {
            if(edtOldPass.text.toString() == edtConfirmPass.text.toString()) {
                profileViewModel.updatePassword(edtNewPass.text.toString(), binding.userEmail.text.toString())
                Toast.makeText(requireContext(), "password changed", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "data is invalid", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setContentView(view)
        dialog.show()
    }


}