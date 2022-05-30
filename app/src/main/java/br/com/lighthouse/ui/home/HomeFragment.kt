package br.com.lighthouse.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.lighthouse.R
import br.com.lighthouse.databinding.FragmentFirstBinding
import br.com.lighthouse.ui.home.adapters.ProductsAdapter
import br.com.lighthouse.util.onQueryTextChange


class HomeFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: HomeViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        (applicationContext as App).appComponent.inject(this)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        initViewModel()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productsAdapter = ProductsAdapter()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.recyclerViewProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


        viewModel.getRecordsObserver().observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }

        setHasOptionsMenu(true)

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       // super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_home, menu)

        val searchItem = menu.findItem(R.id.ic_search)
        val searView = searchItem.actionView as SearchView

        searView.onQueryTextChange {
            viewModel.searchQuery = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ic_sort -> {
                return true
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}