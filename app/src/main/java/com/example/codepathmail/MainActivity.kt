package com.example.codepathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var emails: List<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)
        emails = EmailFetcher.getEmails()

        val adapter = EmailAdapter(emails)
        emailsRv.adapter = adapter
        emailsRv.layoutManager = LinearLayoutManager(this)
//        val sender = findViewById<TextView>(R.id.sender)
//        val summarys = findViewById<TextView>(R.id.emailsummry)
//        val emailTitle = findViewById<TextView>(R.id.emailtitle)
        val loadmorebtn = findViewById<Button>(R.id.LoadMoreBtn)
        loadmorebtn.setOnClickListener {
            //Fetch next 5 emails and display in recycleview
            val newEmails = EmailFetcher.getNext5Emails()
            (emails as MutableList<Email>).addAll(newEmails)
            adapter.notifyDataSetChanged()
        }
    }
}