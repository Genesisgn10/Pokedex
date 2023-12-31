package com.example.pokedex.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokedexBinding
import com.example.pokedex.presenter.MainActivity
import com.example.pokedex.presenter.PokedexViewModel
import com.example.pokedex.presenter.adapter.PokemonAdapter
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.StateLoading
import com.example.utils.StateSuccess
import com.example.utils.addGridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(), ToolbarTextListener {

    private lateinit var binding: FragmentPokedexBinding
    private val viewModel: PokedexViewModel by viewModel()
    private lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showToolbar(true)

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.color_primary)

        viewModel.pokedex.observe(viewLifecycleOwner) {
            when (it) {
                is StateSuccess -> populatePokedex(it.data)
                is StateLoading -> showLoading(it.loading)
                else -> {}
            }
        }

        viewModel.getPokedex()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).setToolbarTextListener(this)
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBarLarge.isVisible = loading
        binding.cardPokemon.isVisible = !loading
    }

    private fun populatePokedex(pokedexModel: MutableList<DetailPokemonModel>) {
        adapter = PokemonAdapter(pokedexModel)
        binding.rvPokemonlist.adapter = adapter
        binding.rvPokemonlist.addGridSpacingItemDecoration(3, 20, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onToolbarTextChanged(text: String) {
        adapter.filterPokemonsByName(text)
    }
}

interface ToolbarTextListener {
    fun onToolbarTextChanged(text: String)
}