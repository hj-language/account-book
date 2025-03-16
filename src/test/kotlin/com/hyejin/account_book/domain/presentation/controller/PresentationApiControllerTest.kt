package com.hyejin.account_book.domain.presentation.controller

import org.assertj.core.api.Assertions.assertThat
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("[API 컨트롤러 테스트]")
class PresentationApiControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
    @Test
    @DisplayName("카테고리 조회 테스트")
    fun testGetCategoriesByType() {
        // given
        val uri = "/api/v1/categories"
        val categoryType = "입금"

        // when
        val mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.get(uri).queryParam("categoryType", categoryType))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        for (i in 0 until jsonArray.length()) {
            assertThat(jsonArray.getJSONObject(i).getString("type")).isEqualTo(categoryType)
        }
    }

}