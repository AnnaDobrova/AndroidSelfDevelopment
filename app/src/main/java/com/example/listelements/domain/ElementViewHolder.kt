package com.example.listelements.domain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listelements.databinding.ItemElementsBinding

// 7 шаг для каждого Вьюхолдера мы прокинули его разметку itemView и листенер, для выполнения каких то
// действий, на конкретным вьюхолдером, в нашему случае удаление конкретного вьюхолдера
class ElementViewHolder(
    itemView: View,
    private val action: DeleteAction
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemElementsBinding = ItemElementsBinding.bind(itemView)

    fun bind(someClass: SomeClass) {
        with(binding) {
            textViewID.text = someClass.id.toString()
            textViewWithName.text = someClass.name
            //8 шаг при нажатии на значок мусорки, его обрабатывает слушатель кликов,  он услышит
            // клик и разрешит выполнить логику которая внутри
            imageViewWithBasket.setOnClickListener {
                //9 шаг логика удаления элемента
                action.onDeleteItemAction(someClass)
            }
        }
    }
}