package com.sorrowbeaver.momo.map.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sorrowbeaver.momo.R
import kotlinx.android.synthetic.main.activity_create_map.*

class CreateMapActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_map)
    setSupportActionBar(toolbar)
  }
}
