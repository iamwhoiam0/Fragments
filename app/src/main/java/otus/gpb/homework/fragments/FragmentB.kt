package otus.gpb.homework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import otus.gpb.homework.fragments.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var isLandScape = false
    private var fragmentBAColor: Int? = null

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLandScape = resources.getBoolean(R.bool.isLandscape)
        fragmentBAColor = savedInstanceState?.getInt("fragmentBA_color")
        if (fragmentBAColor == null) {
            fragmentBAColor = ColorGenerator.generateColor()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentBAColor?.let {
            outState.putInt("fragmentBA_color", it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(fragmentBAColor!!)

        childFragmentManager.setFragmentResultListener("openFragmentBB", this) { _, _ ->
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentBContainer, FragmentBB.newInstance(ColorGenerator.generateColor()))
                .addToBackStack(null)
                .commit()
        }
        childFragmentManager.setFragmentResultListener("openFragmentBA", this) { _, bundle ->
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentBContainer, FragmentBA.newInstance(bundle.getInt("BACKGROUND_COLOR")))
                .addToBackStack(null)
                .commit()
        }

        childFragmentManager.setFragmentResultListener("openLandFragmentBA", this) { _, bundle ->
            childFragmentManager
                .beginTransaction()
                .replace(R.id.container_ba, FragmentBA.newInstance(bundle.getInt("BACKGROUND_COLOR")))
                .addToBackStack(null)
                .commit()
        }

        if (savedInstanceState == null) {
            if (!isLandScape) {
                val childFragment = FragmentBA.newInstance(fragmentBAColor!!)
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentBContainer, childFragment)
                    .commit()

            } else {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_ba, FragmentBA.newInstance(fragmentBAColor!!))
                    .replace(R.id.container_bb, FragmentBB.newInstance(ColorGenerator.generateColor()))
                    .commit()
            }
        }
    }

}