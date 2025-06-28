package otus.gpb.homework.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import otus.gpb.homework.fragments.databinding.FragmentABinding

class FragmentA : Fragment() {

    private lateinit var fragmentAA: FragmentAA
    private lateinit var binding: FragmentABinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount > 0) {
                    childFragmentManager.popBackStack()
                } else {
                    isEnabled = false
                    @Suppress("DEPRECATION")
                    requireActivity().onBackPressed()
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transitionToFragmentAA.setOnClickListener {
            val generatedColor = ColorGenerator.generateColor()
            fragmentAA = FragmentAA.newInstance(generatedColor)
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_a_container, fragmentAA)
                .addToBackStack(null)
                .commit()
        }
    }

}