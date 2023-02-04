package com.example.todo_apps

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter( private val todos: MutableList<ToDo> )
    : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>()
{
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrought(tvTodoTitle:TextView, isChecked:Boolean)
    {
        if (isChecked)
        {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: ToDo)
    {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodo()
    {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply {

            holder.itemView.findViewById<TextView>(R.id.item_todo_text_view).text = curToDo.title
            holder.itemView.findViewById<CheckBox>(R.id.item_todo_chechk_box).isChecked = curToDo.isChecked
            toggleStrikeThrought(holder.itemView.findViewById<TextView>(R.id.item_todo_text_view),
                curToDo.isChecked
                )
            holder.itemView.findViewById<CheckBox>(R.id.item_todo_chechk_box)
                .setOnCheckedChangeListener { compoundButton, isChecked ->
                    toggleStrikeThrought(holder.itemView.findViewById<TextView>(R.id.item_todo_text_view),
                        isChecked
                        )
                    curToDo.isChecked = !curToDo.isChecked
                }

        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}