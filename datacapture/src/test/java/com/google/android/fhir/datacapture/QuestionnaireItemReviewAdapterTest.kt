/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.fhir.datacapture

import android.os.Build
import com.google.android.fhir.datacapture.validation.Valid
import com.google.android.fhir.datacapture.views.QuestionnaireItemViewItem
import com.google.common.truth.Truth.assertThat
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class QuestionnaireItemReviewAdapterTest {
  @Test
  fun `submitting empty list to adapter should return itemCount zero`() {
    val questionnaireItemReviewAdapter = QuestionnaireItemReviewAdapter()
    questionnaireItemReviewAdapter.submitList(listOf())

    assertThat(questionnaireItemReviewAdapter.itemCount).isEqualTo(0)
  }

  @Test
  fun `submitting single items to adapter should return itemCount one`() {
    val questionnaireItemReviewAdapter = QuestionnaireItemReviewAdapter()
    questionnaireItemReviewAdapter.submitList(
      listOf(
        QuestionnaireAdapterItem.Question(
          QuestionnaireItemViewItem(
            Questionnaire.QuestionnaireItemComponent()
              .setType(Questionnaire.QuestionnaireItemType.GROUP),
            QuestionnaireResponse.QuestionnaireResponseItemComponent(),
            validationResult = Valid,
            answersChangedCallback = { _, _, _ -> },
          )
        )
      )
    )

    assertThat(questionnaireItemReviewAdapter.itemCount).isEqualTo(1)
  }

  @Test
  fun `submitting multiple items to adapter should return itemCount greater than zero`() {
    val questionnaireItemReviewAdapter = QuestionnaireItemReviewAdapter()
    questionnaireItemReviewAdapter.submitList(
      listOf(
        QuestionnaireAdapterItem.Question(
          QuestionnaireItemViewItem(
            Questionnaire.QuestionnaireItemComponent()
              .setType(Questionnaire.QuestionnaireItemType.GROUP),
            QuestionnaireResponse.QuestionnaireResponseItemComponent(),
            validationResult = Valid,
            answersChangedCallback = { _, _, _ -> },
          )
        ),
        QuestionnaireAdapterItem.Question(
          QuestionnaireItemViewItem(
            Questionnaire.QuestionnaireItemComponent()
              .setType(Questionnaire.QuestionnaireItemType.DISPLAY),
            QuestionnaireResponse.QuestionnaireResponseItemComponent(),
            validationResult = Valid,
            answersChangedCallback = { _, _, _ -> },
          )
        ),
      )
    )

    assertThat(questionnaireItemReviewAdapter.itemCount).isEqualTo(2)
    assertThat(questionnaireItemReviewAdapter.itemCount).isGreaterThan(0)
  }
}
