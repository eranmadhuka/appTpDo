package com.shaluambasta.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.shaluambasta.noteapp.db.DBOpenHelper

class AddNoteActivity : AppCompatActivity() {

    private lateinit var etTitle: TextInputLayout
    private lateinit var fabSend: FloatingActionButton
    private val dbOpenHelper = DBOpenHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        etTitle = findViewById(R.id.et_title)
        fabSend = findViewById(R.id.fab_send)

        fabSend.setOnClickListener {
            fabSendData()
        }
    }

    private fun fabSendData() {
        if (etTitle.editText?.text.toString().isEmpty()) {
            etTitle.error = "Please enter your Title"
            etTitle.requestFocus()
            return
        }

        if (notEmpty()) {
            dbOpenHelper.addNote(
                etTitle.editText?.text.toString()
            )
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }
    }

    private fun notEmpty(): Boolean {
        return etTitle.editText?.text.toString().isNotEmpty()
    }
}
