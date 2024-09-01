package com.waseem.android_cleanarchitecture.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.clean_architecture_kotlin.data.model.User
import com.waseem.android_cleanarchitecture.presentation.ui.adapter.UserAdapter
import com.waseem.android_cleanarchitecture.presentation.viewmodel.AllUserViewModel
import com.waseem.android_cleanarchitecture.R
import com.waseem.android_cleanarchitecture.data.model.cartsModel.Carts
import com.waseem.android_cleanarchitecture.databinding.ActivityMainBinding
import com.waseem.android_cleanarchitecture.presentation.ui.adapter.CartAdapter
import com.waseem.android_cleanarchitecture.utils.extension.beGone
import com.waseem.android_cleanarchitecture.utils.extension.beVisible
import com.waseem.android_cleanarchitecture.utils.extension.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val allUserViewModel: AllUserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        getObservers()
    }

    private fun initViews() {
        allUserViewModel.waitForServer.observe(this) {
            if (it) {
                binding.progressBar.beVisible()
            } else {
                binding.progressBar.beGone()
            }
        }
        allUserViewModel.apiErrorToast.observe(this) { error ->
            toast(error.toString())
        }

        //call get all user api to show users
        allUserViewModel.getAllUsers()
        allUserViewModel.getAllCArts()
    }

    private fun getObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            allUserViewModel.allCartsDataFlow.collect { data ->
                showCarts(data.carts)
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            allUserViewModel.allUserDataFlow.collect { data ->
                showUsers(data.users)
            }
        }
    }

    private fun showCarts(list: ArrayList<Carts>) {
        binding.cartsRV.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val userAdapter = CartAdapter(list)
            adapter = userAdapter
            userAdapter.notifyDataSetChanged()
        }
    }
    private fun showUsers(list: ArrayList<User>) {
        binding.usersRV.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val userAdapter = UserAdapter(list)
            adapter = UserAdapter(list)
            userAdapter.notifyDataSetChanged()
        }
    }
}