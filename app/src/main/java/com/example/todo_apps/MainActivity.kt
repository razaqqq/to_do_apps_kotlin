package com.example.todo_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvViewTodo:RecyclerView = findViewById(R.id.activity_main_rec_view_to_do_item)
        val btnAdd:Button = findViewById(R.id.activity_main_btn_add_todo)
        val etTodoTitle:EditText = findViewById(R.id.activity_main_ed_text)
        val btnDelete:Button = findViewById(R.id.activity_main_btn_delete_todo)

        toDoAdapter = ToDoAdapter(mutableListOf())

        rvViewTodo.adapter = toDoAdapter
        rvViewTodo.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty())
            {
                val todo = ToDo(todoTitle)
                toDoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDelete.setOnClickListener {
            toDoAdapter.deleteDoneTodo()
        }

    }
}