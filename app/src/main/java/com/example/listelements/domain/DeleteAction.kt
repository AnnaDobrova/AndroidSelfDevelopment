package com.example.listelements.domain

import com.example.listelements.domain.SomeClass

interface DeleteAction {

    // логика удаления  реализована в MainActivity, и то что там внутри будет выполняться,
    // то и будет выполняться для каждого элемента списка, при  нажатии на мусорку конкретного элемента
    fun onDeleteItemAction(someClass: SomeClass)
}