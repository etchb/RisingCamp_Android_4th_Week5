package com.bhongj.rc_week5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_week5.databinding.FragmentAdSlideBinding
import com.bhongj.rc_week5.databinding.FragmentMyProfile04Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [adSlideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class adSlideFragment(val image : Int) : Fragment() {
    private lateinit var _binding : FragmentAdSlideBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgAdSlide.setImageResource(image)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdSlideBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }
}