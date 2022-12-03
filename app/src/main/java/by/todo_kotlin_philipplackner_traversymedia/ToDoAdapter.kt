package by.todo_kotlin_philipplackner_traversymedia

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoAdapter(
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item, parent, false
            )
        )
    }

    fun addTodo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(etTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            etTitle.paintFlags = etTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            etTitle.paintFlags = etTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            etTitle.setText(currentTodo.title)
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(etTitle, currentTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(etTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}