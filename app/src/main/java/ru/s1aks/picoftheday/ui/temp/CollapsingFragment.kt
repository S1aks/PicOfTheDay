package ru.s1aks.picoftheday.ui.temp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.fragment.app.Fragment
import ru.s1aks.picoftheday.databinding.CollapsingFragmentBinding

class CollapsingFragment : Fragment() {
    private lateinit var binding: CollapsingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = CollapsingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = CollapsingFragment()
    }
}