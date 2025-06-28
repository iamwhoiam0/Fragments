package otus.gpb.homework.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentAA : Fragment() {

    private var randomColor: Int = 0
    private lateinit var fragmentAB: FragmentAB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            randomColor = it.getInt(ARG_RAND_COLOR)
        }
        Log.i("myColor", randomColor.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(randomColor)
        fragmentAB = FragmentAB.newInstance(ColorGenerator.generateColor())
        view.findViewById<Button>(R.id.transitionToFragmentAB).setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_a_container, fragmentAB)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        private const val ARG_RAND_COLOR = "random_color"
        fun newInstance(value: Int): FragmentAA {
            val fragment = FragmentAA()
            val args = Bundle()
            args.putInt(ARG_RAND_COLOR, value)
            fragment.arguments = args
            return fragment
        }
    }
}