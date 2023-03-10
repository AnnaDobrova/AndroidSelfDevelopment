package com.example.listelements.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listelements.R

class AdapterListElements(
    var list: List<SomeClass>,
    private val action: DeleteAction
) : RecyclerView.Adapter<ElementViewHolder>() {

    // 5 шаг При создании каждого вьюхолдера мы также прокидываем наш листенер
    // таким образом у каждого Вьюхолдера будет возможность использовать этот листенер.
    // Параллельно  после создания каждого элемента, мы его заполняем с помощью onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        return ElementViewHolder(
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
    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.bind(
            someClass = list[position]
        )
    }

    //4 шаг наш  адаптер понимает с помощью этого метода какой будет размер списка
    // и сколько Вьюхолдеров ему создать
    override fun getItemCount(): Int {
        return list.size
    }

}