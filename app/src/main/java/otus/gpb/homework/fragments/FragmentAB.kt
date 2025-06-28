package otus.gpb.homework.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import otus.gpb.homework.fragments.FragmentAA.Companion

class FragmentAB : Fragment() {

    private var randomColor: Int = 0

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
        return inflater.inflate(R.layout.fragment_a_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(randomColor)
    }

    companion object {
        private const val ARG_RAND_COLOR = "random_color"
        fun newInstance(value: Int): FragmentAB {
            val fragment = FragmentAB()
            val args = Bundle()
            args.putInt(ARG_RAND_COLOR, value)
            fragment.arguments = args
            return fragment
        }
    }
}