package cr.ac.una.googlelocationservice.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cr.ac.una.googlelocationservice.MainActivity
import cr.ac.una.googlelocationservice.R
import cr.ac.una.googlelocationservice.adapter.ListaAdapter
import cr.ac.una.googlelocationservice.databinding.FragmentListBinding
import cr.ac.una.googlelocationservice.entity.Page
import cr.ac.una.googlelocationservice.entity.WikipediaResponse
import cr.ac.una.googlelocationservice.viewModel.PageViewModel
import fuel.Fuel
import fuel.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var notificationManager: NotificationManager


    private lateinit var pageViewModel: PageViewModel
    private lateinit var pages: List<Page>

    private lateinit var searchBox: TextView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        return binding.root
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            "locationServiceChannel",
            "Location Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(serviceChannel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val searchBox: EditText = view.findViewById(R.id.et_search)

        searchBox = view.findViewById(R.id.et_search)

        val searchButton: TextView = view.findViewById(R.id.btn_search)

        pages = mutableListOf<Page>()

        val adapter = ListaAdapter(requireContext(), pages)
        val listView = view.findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        pageViewModel = ViewModelProvider(requireActivity()).get(PageViewModel::class.java)

        pageViewModel.pages.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.updateList(it)
            }
        }

        searchButton.setOnClickListener {
            val search = searchBox.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    pageViewModel.startLoadingPages(search)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Sin resultados", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val page = adapter.getItem(position)
            val action = ListFragmentDirections.actionListFragmentToWebViewFragment(page.title)
            findNavController().navigate(action)
        }

    }

}