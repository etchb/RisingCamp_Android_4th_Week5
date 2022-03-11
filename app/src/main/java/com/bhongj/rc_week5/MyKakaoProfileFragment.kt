package com.bhongj.rc_week5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bhongj.rc_week5.databinding.FragmentMyKakaoProfileBinding
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyKakaoProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyKakaoProfileFragment : Fragment() {
    private lateinit var _binding: FragmentMyKakaoProfileBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMyKakaoProfileBinding.inflate(layoutInflater, container, false)

        binding.btnGoLoginPage.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.putExtra("reset", true)
            startActivity(intent)
        }

        val sharedPreferences = activity?.getSharedPreferences("test", AppCompatActivity.MODE_PRIVATE)
        if (sharedPreferences != null) {
            Log.d("TEST sharedPreferences22", sharedPreferences.getString("userId", "defaultId").toString())
            Log.d("TEST sharedPreferences22", sharedPreferences.getString("imageUrl", "defaultImageUrl").toString())

            Glide.with(requireActivity())
                .load(sharedPreferences.getString("imageUrl", "defaultImageUrl"))
                .into(binding.imgMe)
            binding.txtName.text = sharedPreferences.getString("userId", "defaultId")
        }

        return binding.root
    }
}