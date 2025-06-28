package otus.gpb.homework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import otus.gpb.homework.fragments.databinding.FragmentBABinding
import otus.gpb.homework.fragments.databinding.FragmentBBBinding

private const val BACKGROUND_COLOR = "background_color"

class FragmentBB : Fragment() {

    private var backGroundColor: Int? = null

    private var _binding: FragmentBBBinding? = null
    private val binding get() = _binding!!

    private var isLandScape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            backGroundColor = it.getInt(BACKGROUND_COLOR)
        }

        savedInstanceState?.let {
            backGroundColor = it.getInt(BACKGROUND_COLOR)
        }

        isLandScape = resources.getBoolean(R.bool.isLandscape)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backGroundColor?.let { view.setBackgroundColor(it) }

        parentFragmentManager.setFragmentResultListener("color", this) { _, bundle ->
            backGroundColor = bundle.getInt("color")
            view.setBackgroundColor(backGroundColor!!)
        }

        val bundle = Bundle()
        bundle.putInt("BACKGROUND_COLOR", backGroundColor!!)
        binding.transitionToFragmentBA.setOnClickListener{
            if (isLandScape){
                parentFragmentManager.setFragmentResult("openLandFragmentBA", bundle)
            }else{
                parentFragmentManager.setFragmentResult("openFragmentBA", bundle)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(backGroundColor: Int) =
            FragmentBB().apply {
                arguments = Bundle().apply {
                    putInt(BACKGROUND_COLOR, backGroundColor)
                }
            }
    }
}