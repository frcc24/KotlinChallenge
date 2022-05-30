package br.com.lighthouse.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import br.com.lighthouse.databinding.FragmentFirstBinding
import br.com.lighthouse.ui.home.adapters.ProductsAdapter
import br.com.lighthouse.ui.productui.ProductActivity
import br.com.lighthouse.util.onQueryTextChange


class HomeFragment : Fragment() , ProductsAdapter.onItemClickListener{

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: HomeViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        val productsAdapter = ProductsAdapter(this)

        binding.apply {
            buttonFirst.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            recyclerViewProducts.apply {
                adapter = productsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ItemTouchHelper(object: ItemTouchHelper.SimpleCallback( 0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val product = productsAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onProductSwipe(product)
                    viewModel.getProducts()
                }
            }).attachToRecyclerView(recyclerViewProducts)
        }

        viewModel.getRecordsObserver().observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
            viewModel.getProducts()
        }

        setHasOptionsMenu(true)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_home, menu)

        val searchItem = menu.findItem(R.id.ic_search)
        val searView: SearchView = searchItem.actionView as SearchView

        searView.onQueryTextChange {
            viewModel.searchQuery = it
            viewModel.getProducts()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.ic_sort -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(product: Product) {
        viewModel.onProductSelected(product)
        val intent = Intent(context,ProductActivity::class.java)
        intent.putExtra("product",product)
        startActivity(intent)
    }
}