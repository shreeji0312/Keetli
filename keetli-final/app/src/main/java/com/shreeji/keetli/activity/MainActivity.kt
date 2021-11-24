package com.shreeji.keetli.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.shreeji.keetli.R
import com.shreeji.keetli.adapter.ChatAdapter
import com.shreeji.keetli.adapter.PostAdapter
import com.shreeji.keetli.model.Chat
import com.shreeji.keetli.model.Post
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var postView: RecyclerView
    var postList = ArrayList<Post>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val message = Intent(this@MainActivity, UsersActivity::class.java)
        val setting = findViewById<ImageView>(R.id.setting)
        val postButton = findViewById<ImageView>(R.id.postButton)
        postView = findViewById(R.id.postView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postView.layoutManager = layoutManager

        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Posts")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                postList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val post = dataSnapShot.getValue(Post::class.java)
                    if (post != null) {
                            postList.add(post)
                    }
                }

                val postAdapter = PostAdapter(this@MainActivity, postList)

                postView.adapter = postAdapter
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.message -> {
                    startActivity(message)
                }
            }
            true
        }

        postButton.setOnClickListener{
            val post = Intent(this@MainActivity, PostActivity::class.java)
            startActivity(post)
        }

        setting.setOnClickListener{
            val setting = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(setting)
        }

    }
}