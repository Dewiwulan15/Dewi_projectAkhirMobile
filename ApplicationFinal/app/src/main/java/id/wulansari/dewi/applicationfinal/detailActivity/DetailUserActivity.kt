package id.wulansari.dewi.applicationfinal.detailActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import id.wulansari.dewi.applicationfinal.R
import id.wulansari.dewi.applicationfinal.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    //lateinit memungkinkan untuk mengalihkan inisialisasi properti
    private lateinit var detalBinding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    //object pendamping
    companion object {
        const val EXTRA_USERNAME = "extra_username"

        private val TAB_TITLES = intArrayOf(
            R.string.name,
            R.string.share
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // call the super class onCreate to complete the creation of activity like
        //the view hierarchy
        super.onCreate(savedInstanceState)
        detalBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detalBinding.root)

        //merupakan elemen yang ada dibagian atas layar aktivitas, dengan menggunakan nama Detail User
        val actionbar = supportActionBar
        actionbar?.title = "Detail User"

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val username = intent.getStringExtra(EXTRA_USERNAME) ?: "null"

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel.setUserDetail(username)
        Log.d("Detail", "username: $username")

        viewModel.getUserDetail().observe(this, {
            detalBinding.apply {
                Glide.with(this@DetailUserActivity)
                    .load(it.avatar_url)
                    .centerCrop()
                    .into(imgAvatar)

                tvUsername.text = it.login
                tvName.text = it.name
                tvLocation.text = it.location
                tvCompany.text = it.company
                tvBio.text = it.bio
            }
        })


        detalBinding.btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            val link =
                "https://github.com/$username"
            share.putExtra(Intent.EXTRA_SUBJECT, "More Info ")
            share.putExtra(Intent.EXTRA_TEXT, link)
            startActivity(Intent.createChooser(share, "Share to"))
        }
    }

    //called when the up button is pressed. Just the pop back stack.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}