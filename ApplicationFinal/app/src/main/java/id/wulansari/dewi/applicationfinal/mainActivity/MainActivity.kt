package id.wulansari.dewi.applicationfinal.mainActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.wulansari.dewi.applicationfinal.databinding.ActivityMainBinding
import id.wulansari.dewi.applicationfinal.detailActivity.DetailUserActivity
import id.wulansari.dewi.applicationfinal.model.User

//mengatur tampilan konten
class MainActivity : AppCompatActivity() {

    //menggunakan variabel viewBinding, viewModel, dan adapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)// Always call the superclass first
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val actionbar = supportActionBar
        actionbar?.title = "Search User"

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
           //mengatur tampilan konten, dengan baris ini
            override fun onItemClicked(user: User) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                with(intent) {
                    putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                }
                startActivity(intent)
            }

        })

        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)


        binding.apply {

            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter

            imgBtnSearch.setOnClickListener {

                searchUser()
            }

            etQuery.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    searchUser()

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v?.windowToken, 0)

                    return@OnKeyListener true
                }
                false
            })

        }

        viewModel.getListUsers().observe(this, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)

            }
        })

    }

    //method untuk mencari user, dengan text query yang diberikan
    private fun searchUser() {
        binding.apply {
            val searchText = etQuery.text.toString()
            if (searchText.isEmpty()) return
            showLoading(true)
            viewModel.findUsers(searchText)
        }
    }

    //method untuk menampilkan hasil loading, menggunakan viewBinding
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}