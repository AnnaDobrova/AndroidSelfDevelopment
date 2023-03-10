package com.example.listelements.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.listelements.R
import com.example.listelements.domain.SomeClass

class SomeClassAdapter(
    private val action: DeleteAction
) : ListAdapter<SomeClass, SomeClassViewHolder>(SomeClassDiffCallback()) {

    // 5 шаг При создании каждого вьюхолдера мы также прокидываем наш листенер
    // таким образом у каждого Вьюхолдера будет возможность использовать этот листенер.
    // Параллельно  после создания каждого элемента, мы его заполняем с помощью onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SomeClassViewHolder {
        return SomeClassViewHolder(
            // itemView  это надувание нашего xml макета  а именно item_elements
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_elements,
                parent,
                false
            ),
            // слушатель действий  для каждого вьюхолдера. С помощью него мы сможем выполнить какое то действие
            // в нашем случае удаление по конкретному элементу
            action = action
        )
    }

    // 6 метод заполняет  данными наши ВьюХолдеры с помощью информации из списка
    override fun onBindViewHolder(holder: SomeClassViewHolder, position: Int) {
        holder.bind(
            // getItem(position) знает все о нашем списке элементов который мы загрузили.
            // по сути getItem(position) == нашему списку элементов
            someClass = getItem(position)
        )
    }

    // принцип работы  hashcode+equals
    private class SomeClassDiffCallback : DiffUtil.ItemCallback<SomeClass>() {


        // наш хэшкод, т.е. сначала дергается он и проверяет по уникальному идентификатору
        // в нашем случае по id. Если они не равны значит элементы разные, и дифф утил, заменит
        // старый элемент на новый(под капотом)
        override fun areItemsTheSame(oldItem: SomeClass, newItem: SomeClass): Boolean {
            return oldItem.id == newItem.id
        }

        // Если наши id были равны, то у нас дергается этот метод, он аналог equals, который проверяет
        // наш объект целеком. И при необходимости заменяет
        override fun areContentsTheSame(oldItem: SomeClass, newItem: SomeClass): Boolean {
            return oldItem == newItem
        }
    }
}