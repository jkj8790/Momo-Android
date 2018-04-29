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

class PostEntityTest {

  private val currentTime = Date().time

  private val samplePost = PostEntity(
    0, 0, "photo1", "description", Date(currentTime)
  )

  @Mock
  private val mockCursor = mock<Cursor>()

  @Before
  fun setUp() {
    `when`(mockCursor.getLong(0)).thenReturn(samplePost.pk)
    `when`(mockCursor.getLong(1)).thenReturn(samplePost.pin)
    `when`(mockCursor.getString(2)).thenReturn(samplePost.photo)
    `when`(mockCursor.getString(3)).thenReturn(samplePost.description)
    `when`(mockCursor.getLong(4)).thenReturn(currentTime)
  }

  @Test
  fun testPostEntityCursorConstructor() {
    val postEntityFromCursor = PostEntity(mockCursor)

    assertThat(postEntityFromCursor, `is`(samplePost))
  }
}
