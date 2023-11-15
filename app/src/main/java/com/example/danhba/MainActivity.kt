package com.example.danhba

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var danhBaAdapter: DanhBaAdapter
    private var danhBaList = ArrayList<DanhBaModel>()
    var actionMode: ActionMode? = null
    val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            val selectedContact = danhBaAdapter.getSelectedContact() // Lấy mục được chọn từ adapter

            when (item?.itemId) {
                R.id.action_phone -> {
                    // Thực hiện cuộc gọi điện
                    val dialIntent =
                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:${selectedContact?.phone}"))
                    startActivity(dialIntent)
                }

                R.id.action_message -> {
                    // Thực hiện gửi tin nhắn SMS
                    val smsIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("sms:${selectedContact?.phone}"))
                    startActivity(smsIntent)
                }

                R.id.action_email -> {
                    // Thực hiện gửi email
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${selectedContact?.email}")
                        putExtra(Intent.EXTRA_SUBJECT, "Chủ đề email")
                        putExtra(Intent.EXTRA_TEXT, "Nội dung email")
                    }
                    startActivity(emailIntent)
                }
            }
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateFakeData()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        danhBaAdapter = DanhBaAdapter(danhBaList, { item ->
            // Xử lý khi item được click
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("name", item.username)
            intent.putExtra("phone", item.phone)
            intent.putExtra("email", item.email)
            startActivity(intent)
        }, { item ->
            if (actionMode == null) {
                actionMode = startSupportActionMode(actionModeCallback)
            }
            true
        })
        recyclerView.adapter = danhBaAdapter
    }

    private fun generateFakeData() {
        danhBaList.add(DanhBaModel(1, "John Doe", "123456789", "john.doe@example.com"))
        danhBaList.add(DanhBaModel(2, "Jane Doe", "987654321", "jane.doe@example.com"))
        danhBaList.add(DanhBaModel(3, "Alice Smith", "111222333", "alice.smith@example.com"))
        danhBaList.add(DanhBaModel(4, "Bob Johnson", "444555666", "bob.johnson@example.com"))
        danhBaList.add(DanhBaModel(5, "Eve Anderson", "777888999", "eve.anderson@example.com"))
        danhBaList.add(DanhBaModel(6, "Charlie Brown", "555444333", "charlie.brown@example.com"))
        danhBaList.add(DanhBaModel(7, "Diana Miller", "333222111", "diana.miller@example.com"))
        danhBaList.add(DanhBaModel(8, "Frank Wilson", "999888777", "frank.wilson@example.com"))
        danhBaList.add(DanhBaModel(9, "Grace Davis", "666777888", "grace.davis@example.com"))
        danhBaList.add(DanhBaModel(10, "Henry Moore", "222333444", "henry.moore@example.com"))
    }
}