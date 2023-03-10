package com.example.listelements.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listelements.databinding.ActivityMainBinding
import com.example.listelements.databinding.ItemElementsBinding
import com.example.listelements.presentation.adapter.SomeClassAdapter
import com.example.listelements.presentation.adapter.DeleteAction
import com.example.listelements.domain.SomeClass

//2 шаг , полиморфизм, т.е.  чтобы мы могли использовать нашу Активити и с ее помощью
// реализовывать нашу логику, мы должны реализовать интерфейс DeleteListener
// как  пример это аналог  Interface Animal  и класс Cat, где интерфейс Animal - DeleteListener
// а класс Cat - MainActivity
class MainActivity : AppCompatActivity(), DeleteAction {

    private lateinit var bindingFirstScreen: ActivityMainBinding
    private lateinit var bindingSecondScreen: ItemElementsBinding

    private var someClassAdapter: SomeClassAdapter? = null

    // 1 шаг заполнение списка элементов
    val list = mutableListOf(
        SomeClass(1, "Стол"),
        SomeClass(2, "Стул"),
        SomeClass(3, "Ручка"),
        SomeClass(4, "Мышка"),
        SomeClass(5, "Компьютер"),
        SomeClass(6, "Телевизор"),
        SomeClass(7, "Ноут"),
        SomeClass(8, "Табуретка"),
        SomeClass(9, "Эмулятор"),
        SomeClass(10, "Аня"),
        SomeClass(11, "Гей"),
        SomeClass(12, "Артем"),
        SomeClass(13, "Вадим"),
        SomeClass(14, "Чмо"),
        SomeClass(15, "Цветы"),
        SomeClass(16, "Я"),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFirstScreen = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingFirstScreen.root
        setContentView(view)

        bindingSecondScreen = ItemElementsBinding.inflate(layoutInflater)
        bindingSecondScreen.root

        initRecycler()

        //3 шаг заполнение адаптера списком элементов
        someClassAdapter?.submitList(list)
    }

    private fun initRecycler() {
        //2 шаг мы прокидываем в адаптер наш слушатель удаления элементов листенер
        // для того чтобы у каждого элемента был слушатель кнопки удаления
        someClassAdapter = SomeClassAdapter(action = this@MainActivity)

        with(bindingFirstScreen.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = someClassAdapter
        }
    }

    override fun onDeleteItemAction(someClass: SomeClass) {
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
        list.remove(someClass)
        someClassAdapter?.submitList(list)
        someClassAdapter?.notifyDataSetChanged()
    }
}