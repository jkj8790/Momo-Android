package com.sorrowbeaver.momo.map.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.sorrowbeaver.momo.MomoApplication
import com.sorrowbeaver.momo.R
import kotlinx.android.synthetic.main.activity_create_map.*
import kotlinx.android.synthetic.main.content_create_map.*
import javax.inject.Inject

class CreateMapActivity : AppCompatActivity(), CreateMapContract.View {
  @Inject
  lateinit var presenter: CreateMapContract.Presenter
  private var doneButton: MenuItem? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_map)
    setSupportActionBar(toolbar)

    (application as MomoApplication)
      .component
      .createMapComponent()
      .createMapModule(CreateMapModule(this))
      .build()
      .inject(this)

    nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.onNameChanged(s?.toString().orEmpty())
      }
    })
  }

  override fun showLoading() {
  }

  override fun hideLoading() {
  }

  override fun showSuccessToast() {
    Toast.makeText(this, R.string.done, Toast.LENGTH_SHORT).show()
  }

  override fun close() {
    finish()
  }

  override fun setNameLengthError(error: Boolean) {
    if (error) {
      nameInputLayout.error = getString(R.string.name_length_error)
    } else {
      nameInputLayout.error = null
    }
  }

  override fun setDoneButtonEnabled(enabled: Boolean) {
    doneButton?.isEnabled = enabled
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.create_map, menu)
    doneButton = menu?.findItem(R.id.menu_done)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      R.id.menu_done -> {
        presenter.createMap(
          nameEditText.text.toString(),
          descriptionEditText.text.toString(),
          privateCheckBox.isChecked
        )
        true
      }
      else -> {
        return super.onOptionsItemSelected(item)
      }
    }
  }

  override fun showError() {
    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
  }
}
