package com.ufuoma.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myContactAdapter: ContactAdapter
    private lateinit var myContactList: MutableList<ContactModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java, "contact-database"
        ) .allowMainThreadQueries().build()
        val contactDAO = db.contactDao()




        contactDAO.getallShoppingItems().observe(this, {it:List<ContactModel>!
            myContactAdapter = ContactAdapter(myContactList)
            binding.recyclerView.adapter = myContactAdapter
            myContactAdapter.notifyDataSetChanged()
        })

        binding.button.setOnClickListener {
            val name: String = binding.editText.text.toString()
            val phone :String = binding.editText2.text.toString()

            val shoppingItem = ContactModel(name,phone)
            contactDAO.addShoppingItem(shoppingItem)

            myContactList.add(shoppingItem)
            myContactAdapter.notifyDataSetChanged()
        }


    }
}