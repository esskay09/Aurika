package com.example.aurika.ui

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aurika.R
import com.example.aurika.databinding.FragmentMainBinding
import com.example.aurika.viewmodels.MainFragmentViewModel


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )

        setHasOptionsMenu(true)

        val viewModel: MainFragmentViewModel by viewModels()

        val adapter = RecyclerViewAdapterBooks(BookClickListener {
            onBookClicked(it)
        })

        val divider =
            DividerItemDecoration(binding.recyclerView.context, LinearLayoutManager.VERTICAL)

        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.adapter = adapter

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.livelist.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        /*viewModel.progressBarVisibilty.observe(viewLifecycleOwner, Observer {
            when(it) {
                viewModel.VISIBLE -> binding.progressBar.visibility = ProgressBar.VISIBLE
                viewModel.INVISIBLE -> binding.progressBar.visibility = ProgressBar.INVISIBLE
            }
        })*/


        setUpSearchButtonEditText(binding, viewModel)

        return binding.root


    }

    private fun setUpSearchButtonEditText(
        binding: FragmentMainBinding,
        viewModel: MainFragmentViewModel
    ) {
        binding.queryinput.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) { //TODO

                if (binding.queryinput.text.toString()
                        .isNotBlank()
                ) viewModel.searchBook(binding.queryinput.text.toString())

            }
            false
        })
    }


    private fun onBookClicked(bookClicked: BookDomain) {
        val action = MainFragmentDirections.actionMainFragmentToWebFragment(
            "https://b-ok.asia/s/{${bookClicked.name}}",
            bookClicked.name
        )
        this.findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            this.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}