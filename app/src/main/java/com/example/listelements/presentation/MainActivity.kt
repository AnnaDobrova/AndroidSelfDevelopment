package com.example.listelements.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listelements.databinding.ActivityMainBinding
import com.example.listelements.databinding.ItemElementsBinding
import com.example.listelements.domain.AdapterListElements
import com.example.listelements.domain.DeleteAction
import com.example.listelements.domain.SomeClass

//2 шаг , полиморфизм, т.е.  чтобы мы могли использовать нашу Активити и с ее помощью
// реализовывать нашу логику, мы должны реализовать интерфейс DeleteListener
// как  пример это аналог  Interface Animal  и класс Cat, где интерфейс Animal - DeleteListener
// а класс Cat - MainActivity
class MainActivity : AppCompatActivity(), DeleteAction {

    private lateinit var bindingFirstScreen: ActivityMainBinding
    private lateinit var bindingSecondScreen: ItemElementsBinding

    private var adapterListElements: AdapterListElements? = null

    // 1 шаг заполнение списка элементов
    val list = mutableListOf(
        SomeClass(1, "Стол"),
        SomeClass(2, "Стул"),
        SomeClass(3, "Ручка"),
        SomeClass(4, "Мышка"),
        SomeClass(5, "Компьютер")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFirstScreen = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingFirstScreen.root
        setContentView(view)

        bindingSecondScreen = ItemElementsBinding.inflate(layoutInflater)
        bindingSecondScreen.root


        initRecycler()
    }

    private fun initRecycler() {
        //Заполнение  нашего адаптера  списком элементов, и мы прокидываем туда листенер
        // для того чтобы у каждого элемента был слушатель кнопки удаления
        adapterListElements = AdapterListElements(
            list = list,
            action = this@MainActivity
        )
        with(bindingFirstScreen.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterListElements
        }
    }

    override fun onDeleteItemAction(someClass: SomeClass) {
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
        list.remove(someClass)
        adapterListElements?.notifyDataSetChanged()

    }
}