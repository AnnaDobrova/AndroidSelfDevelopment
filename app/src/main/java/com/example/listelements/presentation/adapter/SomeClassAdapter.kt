package com.example.listelements.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.listelements.R
import com.example.listelements.domain.SomeClass

class SomeClassAdapter(
    private val action: DeleteAction
) : RecyclerView.Adapter<SomeClassViewHolder>() {

    private var someList = mutableListOf<SomeClass>()

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
            someClass = someList[position]
        )
    }

    //апдейтим данные старого списка новыми данными, к примеру удалили элемент
    fun updateList(newList: List<SomeClass>) {
        val diffCallback = SomeClassDiffCallback(someList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.someList.clear()
        this.someList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    // получить текущий список, если где то это будет нужно
    fun getCurrentList() = someList

    // получить размер актуального списка
    override fun getItemCount(): Int = someList.size

}

// принцип работы  hashcode+equals
class SomeClassDiffCallback(
    private val oldList: List<SomeClass>,
    private val newList: List<SomeClass>
) : DiffUtil.Callback() {

    // наш старый размер списка
    override fun getOldListSize(): Int = oldList.size
    // наш новый размер списка
    override fun getNewListSize(): Int = newList.size

    // наш хэшкод, т.е. сначала дергается он и проверяет по уникальному идентификатору
    // в нашем случае по id. Если они не равны значит элементы разные, и дифф утил, заменит
    // старый элемент на новый(под капотом)
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.id == newUser.id
    }

    // Если наши id были равны, то у нас дергается этот метод, он аналог equals, который проверяет
    // наш объект целеком. И при необходимости заменяет
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }
}