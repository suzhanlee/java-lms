package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        Question question = new Question();
        answers = new Answers(
                Arrays.asList(new Answer(1L, JAVAJIGI, question, "댓글1"),
                        new Answer(2L, JAVAJIGI, question, "댓글2")));
    }
    @Test
    @DisplayName("댓글을 삭제하면 삭제 히스토리 목록을 생성한다.")
    void create_answer_delete_history() {
        // given
        // when
        List<DeleteHistory> result = answers.createAnswerDeleteHistory();

        // then
        assertThat(result).hasSize(2)
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactlyInAnyOrder(
                        tuple(ANSWER, 1L, JAVAJIGI),
                        tuple(ANSWER, 2L, JAVAJIGI)
                );
    }
}