package com.example.pokedex.presenter.fragment

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FragmentPokedexBinding
import com.example.pokedex.presenter.MainActivity
import com.example.pokedex.presenter.PokedexViewModel
import com.example.pokedex.presenter.adapter.Adapter
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.StateLoading
import com.example.utils.StateSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding

    private val viewModel: PokedexViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showToolbar(true)

        viewModel.pokedex.observe(viewLifecycleOwner) {
            when (it) {
                is StateSuccess -> populatePokedex(it.data)
                is StateLoading -> showLoading(it.loading)
                else -> {}
            }
        }

        viewModel.getPokedex()
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBarLarge.isVisible = loading
        binding.cardPokemon.isVisible = !loading
    }

    private fun populatePokedex(pokedexModel: MutableList<DetailPokemonModel>) {
        val grid = GridLayoutManager(context, 3)
        val adapter = Adapter(pokedexModel)

        binding.rvPokemonlist.layoutManager = grid
        binding.rvPokemonlist.adapter = adapter

        binding.rvPokemonlist.addItemDecoration(GridSpacingItemDecoration(3, 20, false))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }

        // Ajuste adicional para espaçamento entre linhas
        if (position >= spanCount) {
            outRect.top = spacing
        }
    }
}