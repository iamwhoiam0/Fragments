package otus.gpb.homework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import otus.gpb.homework.fragments.databinding.FragmentBABinding

private const val BACKGROUND_COLOR = "background_color"

class FragmentBA : Fragment() {

    private var backGroundColor: Int? = null

    private var _binding: FragmentBABinding? = null
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
        _binding = FragmentBABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backGroundColor?.let { view.setBackgroundColor(it) }

        binding.transitionToFragmentBB.visibility = if (isLandScape) View.GONE else View.VISIBLE

        parentFragmentManager.setFragmentResultListener("color", this) { _, bundle ->
            backGroundColor = bundle.getInt("color")
            view.setBackgroundColor(backGroundColor!!)
        }

        binding.transitionToFragmentBB.setOnClickListener{
            parentFragmentManager.setFragmentResult("openFragmentBB", Bundle.EMPTY)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        backGroundColor?.let {
            outState.putInt(BACKGROUND_COLOR, it)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(backGroundColor: Int) =
            FragmentBA().apply {
                arguments = Bundle().apply {
                    putInt(BACKGROUND_COLOR, backGroundColor)
                }
            }
    }
}