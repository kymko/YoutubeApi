package com.example.youtubeapi.ui.playlist

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlayListBinding
import com.example.youtubeapi.ui.playlist.adapter.PlayListAdapter

class PlayListActivity : BaseActivity<ActivityPlayListBinding>() {

    private var viewModel:PlayListViewModel? = null
    private lateinit var playListAdapter:PlayListAdapter

    override fun setupUI() {
        viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)
    }

    override fun setupLiveData() {
        viewModel?.fetchAllPlayLists()?.observe(this){ response->

            if (response != null) {
                Log.d("tag","response : $response")
                playListAdapter.addPlayList(response.items)
            }

        }
        playListAdapter = PlayListAdapter()

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@PlayListActivity)
            adapter = playListAdapter
        }
    }


    override fun showDisconnectState() {

    }

    override fun inflateBinding(inflater: LayoutInflater): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(layoutInflater)
    }
}


//1. Создать свой ApiKey и ознакомиться с документацией
//2. Добавить в класс playlist поле "items", отрисовать первых 2 экрана из фигмы (Проверка на интернет, и список всех PlayList)
//3. сделать переход на новую активити и передаете туда id и её отображаете тостом
//
//Также прочитайте про корутины желательно
//
//Доп: в PlayListActivity попробуйте реализовать пагинацию с помощью ViewType с RecyclerView