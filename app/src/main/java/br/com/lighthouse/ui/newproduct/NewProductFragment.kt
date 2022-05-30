package br.com.lighthouse.ui.newproduct

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import br.com.lighthouse.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar


class NewProductFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var viewModel: NewProductViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewModel()

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonSave.setOnClickListener {


            if( !binding.txtProductName.text.isEmpty() &&
                !binding.txtProductType.text.isEmpty() &&
                !binding.editTextQtd.text.isEmpty()) {

                val product = Product(binding.txtProductName.text.toString(),
                    binding.txtProductType.text.toString(),
                    (binding.editTextQtd.text.toString()).toInt(),
                    "")

                println(product)

                viewModel.insertRecord(product)

                Snackbar.make(it, "Product Added to DB", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .show()

                clearFields()


            }else{
                Snackbar.make(it, "All fields are required", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun clearFields() {
        binding.txtProductName.setText("")
        binding.txtProductType.setText("")
        binding.editTextQtd.setText("")

    }


    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(NewProductViewModel::class.java)
        viewModel.getRecordsObserver().observe(viewLifecycleOwner, object :Observer<List<Product>>{
            override fun onChanged(t: List<Product>?) {
                t?.forEach {
                  println(it.productName)
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}