package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.MomoMap
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.Date

class MomoMapModelDataMapperTest {

  private val fakeId = 0L
  private val fakeName = "name"
  private val fakeDescription = "description"
  private val fakeIsPrivate = true
  private val fakeAuthorId = 1L
  private val fakePinIds = listOf(2L)
  private val fakeCreatedDate = Date()

  @Test
  fun testTransform() {
    val momoMap = MomoMap(
      fakeId, fakeName, fakeDescription, fakeIsPrivate,
      fakeAuthorId, fakePinIds, fakeCreatedDate
    )
    val mapper = MomoMapModelDataMapper()

    val mapModel = mapper.transform(momoMap)

    assertThat(mapModel.id, `is`(fakeId))
    assertThat(mapModel.name, `is`(fakeName))
    assertThat(mapModel.description, `is`(fakeDescription))
    assertThat(mapModel.isPrivate, `is`(fakeIsPrivate))
    assertThat(mapModel.authorId, `is`(fakeAuthorId))
    assertThat(mapModel.pinIds, `is`(fakePinIds))
    assertThat(mapModel.createdDate, `is`(fakeCreatedDate))
  }
}
