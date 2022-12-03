package by.todo_kotlin_philipplackner_traversymedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDoAdapter = ToDoAdapter(mutableListOf())

        rvItems.adapter = toDoAdapter
        rvItems.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val todoTitle = etTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = ToDo(todoTitle)
                toDoAdapter.addTodo(todo)
                etTitle.text.clear()
            }
        }

        btnDelete.setOnClickListener{
            toDoAdapter.deleteDoneTodos()
        }
    }
}