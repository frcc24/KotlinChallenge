package br.com.lighthouse.ui.newproduct

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.ETC1.encodeImage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.lighthouse.R
import br.com.lighthouse.data.Product
import br.com.lighthouse.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar


class NewProductFragment : Fragment() {

    private var img: String = ""
    private var _binding: FragmentSecondBinding? = null
    lateinit var viewModel: NewProductViewModel
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

        binding.buttonAddImg.setOnClickListener{
            openGalleryForImage()
        }

        binding.buttonSave.setOnClickListener {
            saveProduct(it)
        }
    }

    private fun saveProduct(it: View) {

        if( binding.txtProductName.text.isNotEmpty() &&
            binding.txtProductType.text.isNotEmpty() &&
            binding.editTextQtd.text.isNotEmpty() &&
            img != "") {

            val product = Product(binding.txtProductName.text.toString(),
                binding.txtProductType.text.toString(),
                (binding.editTextQtd.text.toString()).toInt(),
                img)

            println(product)

            viewModel.insertRecord(product)
            viewModel.getAllRecords()

            Snackbar.make(it, "Product Added to DB", Snackbar.LENGTH_LONG)
                .show()

            clearFields()

        }else{
            Snackbar.make(it, "All fields are required", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 0 ){
            val imgUri:Uri = data?.data!!
            println("onActivityResult =============== ")
            println("onActivityResult =============== ${imgUri.toString()}")
            img = imgUri.toString()
            binding.imageView.setImageURI(imgUri)
        }
    }

    private fun clearFields() {
        binding.txtProductName.setText("")
        binding.txtProductType.setText("")
        binding.editTextQtd.setText("")
        binding.imageView.setImageURI(null)
    }


    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(NewProductViewModel::class.java)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}