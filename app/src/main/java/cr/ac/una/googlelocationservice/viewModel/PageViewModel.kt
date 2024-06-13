package cr.ac.una.googlelocationservice.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import cr.ac.una.googlelocationservice.entity.Page
import cr.ac.una.googlelocationservice.entity.WikipediaResponse
import cr.ac.una.googlelocationservice.service.WikipediaService
import fuel.Fuel
import fuel.get

class PageViewModel : ViewModel() {
    private var _pages: MutableLiveData<List<Page>?> = MutableLiveData()
    var pages = _pages

    var wikipediaService = WikipediaService()

    private val gson = Gson()


    suspend fun startLoadingPages(title: String) {
        _pages.postValue(listOf())
        var lista = searchPages(title)
        Log.e("AAA", "$lista")
        _pages.postValue(lista)
    }

    private suspend fun searchPages(title: String): ArrayList<Page>? {
        return wikipediaService.apiService.getRelatedPages(title).pages
    }

    fun getPages() {

    }
}