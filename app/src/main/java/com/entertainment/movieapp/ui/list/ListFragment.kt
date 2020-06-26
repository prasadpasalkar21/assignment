package com.entertainment.movieapp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ccpp.shared.core.exception.Failure
import com.ccpp.shared.core.result.EventObserver
import com.ccpp.shared.entities.Row
import com.ccpp.shared.util.viewModelProvider
import com.entertainment.movieapp.R
import com.entertainment.movieapp.base.BaseFragment
import com.entertainment.movieapp.databinding.FragmentListBinding
import com.entertainment.movieapp.ui.list.adapter.ListAdapter
import javax.inject.Inject


class ListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fragmentViewModel: ListViewModel

    private lateinit var binding: FragmentListBinding

    private lateinit var layoutManger: LinearLayoutManager

    private lateinit var listAdapter: ListAdapter

    var arrayList : ArrayList<Row> = arrayListOf()

    var swipeCount = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            viewModel = fragmentViewModel
            lifecycleOwner = this@ListFragment

            layoutManger = LinearLayoutManager(context)

            recycler.layoutManager = layoutManger

            listAdapter = ListAdapter(arrayList)
            recycler.adapter = listAdapter

            fragmentViewModel.getMovieList()

            fragmentViewModel.callGetMovieListEvent.observe(viewLifecycleOwner,EventObserver{
                toolbar.title.text = it.title.toString()
                arrayList = it.rows as ArrayList<Row>
                listAdapter.addData(arrayList)
            })

            fragmentViewModel.loading.observe(viewLifecycleOwner,EventObserver{
                loadingDialog.loading(it)
            })

            fragmentViewModel.failure.observe(viewLifecycleOwner,EventObserver{

                val message = when (it) {

                    Failure.ServerError().javaClass.toString() -> getString(R.string.unexpected_mobile_error)
                    Failure.NetworkConnection().javaClass.toString() -> getString(R.string.common_error_internet_not_connect)

                    else -> {
                        it
                    }
                }

                if (message != "") {
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                }
            })


            swipeRefreshLayout.setOnRefreshListener {

                swipeCount += 1;
                if (swipeCount > 0) {
//                    blogs.add(Blog("Blog Title $swipeCount", "Description : Blog description goes here"))
//                    Toast.makeText(this, "Swipe called", Toast.LENGTH_SHORT).show()
                }
                listAdapter.notifyDataSetChanged()

                swipeRefreshLayout.isRefreshing = false
            }

        }

        return binding.root
    }

}