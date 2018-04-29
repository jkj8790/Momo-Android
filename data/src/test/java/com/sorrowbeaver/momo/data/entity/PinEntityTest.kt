package com.sorrowbeaver.momo.data.entity

import android.database.Cursor
import com.nhaarman.mockito_kotlin.mock
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.Date

class PinEntityTest {

  private val currentDate = Date()

  private val samplePin = PinEntity(
    0, "pin", 0, currentDate, 0, "author", 0, emptyList()
  )

  @Mock
  private val mockCursor = mock<Cursor>()

  @Before
  fun setUp() {
    `when`(mockCursor.getLong(0)).thenReturn(samplePin.id)
    `when`(mockCursor.getString(1)).thenReturn(samplePin.name)
    `when`(mockCursor.getInt(2)).thenReturn(samplePin.pinLabel)
    `when`(mockCursor.getLong(3)).thenReturn(samplePin.createdAt.time)
    `when`(mockCursor.getLong(4)).thenReturn(samplePin.authorId)
    `when`(mockCursor.getString(5)).thenReturn(samplePin.authorName)
    `when`(mockCursor.getLong(6)).thenReturn(samplePin.map)
  }

  @Test
  fun testPinEntityCursorConstructor() {
    val result = PinEntity(mockCursor)

    assertThat(result, `is`(samplePin))
  }
}
