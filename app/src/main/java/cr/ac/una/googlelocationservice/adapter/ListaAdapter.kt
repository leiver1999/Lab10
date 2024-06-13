package cr.ac.una.googlelocationservice.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cr.ac.una.googlelocationservice.R
import cr.ac.una.googlelocationservice.entity.Page
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import java.net.HttpURLConnection
import java.net.URL

class ListaAdapter(
    private val context: Context,
    private var pages: List<Page>,
) : BaseAdapter() {

    fun updateList(newPages: List<Page>) {
        pages = newPages
        notifyDataSetChanged()
    }

    override fun getCount(): Int = pages.size

    override fun getItem(position: Int): Page = pages[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        val image = view.findViewById<ImageView>(R.id.pageImage)
        val pageTitle = view.findViewById<TextView>(R.id.pageTitle)
        val pageDescription = view.findViewById<TextView>(R.id.pageDescription)


        var page = getItem(position)

        pageTitle.text = page.normalizedtitle
        pageDescription.text = page.extract


        CoroutineScope(Dispatchers.IO).launch {

            var imageUrl = page.thumbnail?.source

            if (imageUrl == null) {//si no hay imagen en la pagina se le asigna una imagen por defecto
                imageUrl = "https://ps.w.org/replace-broken-images/assets/icon-256x256.png"
            }

            val bitmap = urlToBitmap(imageUrl)
            withContext(Dispatchers.Main) {
                image.setImageBitmap(bitmap)
            }
        }

        return view
    }

    private fun urlToBitmap(source: String?): Bitmap? {
        try {
            val url = URL(source)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: java.io.IOException) {
            println(e)
        }
        // return any image from the drawable folder
        return BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background)
    }
}