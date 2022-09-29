package com.example.mytodobeta

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

// FragmentのプライマリーコンストラクタでレイアウトXMLを指定
class MainFragment:Fragment(R.layout.main_fragment) {
    // デリゲートを使ってviewmodelをプロパティとしてもたせる
    private val vm: MainViewModel by viewModels()
}