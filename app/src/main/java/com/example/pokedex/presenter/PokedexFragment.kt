package com.example.pokedex.presenter

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FragmentPokedexBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding

    val viewModel: PokedexViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokedex.observe(viewLifecycleOwner) {
            populatePokedex(it)
        }

        viewModel.getPokedex()

    }

    private fun populatePokedex(pokedexModel: PokedexModel) {
        val grid = GridLayoutManager(context, 3)
        val adapter = AdapterGridList(pokedexModel.results)

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