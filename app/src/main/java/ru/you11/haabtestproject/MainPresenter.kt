package ru.you11.haabtestproject

class MainPresenter(private val fragment: MainFragment): MainContract.Presenter {

    init {
        fragment.presenter = this
    }


}