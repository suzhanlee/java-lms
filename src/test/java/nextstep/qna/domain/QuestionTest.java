package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;
import static nextstep.qna.domain.Question.DELETE_QUESTION_EXCEPTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자와 사용자가 다르면 예외를 던진다.")
    void not_same_user() {
        // when // then
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage(DELETE_QUESTION_EXCEPTION);
    }

    @Test
    @DisplayName("사용자와 댓글 작성자 중 다른게 있으면 예외를 던진다.")
    void cannot_delete_question() {
        // given
        Question question = new Question(SANJIGI, "title2", "댓글1");
        question.addAnswer(new Answer(SANJIGI, question, "댓글1"));

        // when // then
        assertThatThrownBy(() -> question.delete(JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("사용자와 질문 작성자, 댓글 작성자가 모두 같으면 질문과 댓글 상태를 삭제로 바꾼다.")
    void change_status() {
        // given
        Question testQuestion = new Question(JAVAJIGI, "title1", "contents1");
        testQuestion.addAnswer(new Answer(JAVAJIGI, testQuestion, "댓글1"));
        // when
        testQuestion.delete(JAVAJIGI);
        // then
        assertThat(testQuestion.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제하면 삭제 히스토리 목록을 생성한다.")
    void create_history() {
        // given
        Question testQuestion = new Question(1L, JAVAJIGI, "title1", "contents1");
        testQuestion.addAnswer(new Answer(2L, JAVAJIGI, testQuestion, "댓글1"));
        testQuestion.addAnswer(new Answer(3L, JAVAJIGI, testQuestion, "댓글2"));

        // when
        List<DeleteHistory> result = testQuestion.writeDeleteHistory();

        // then
        assertThat(result).hasSize(3)
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactlyInAnyOrder(
                        tuple(QUESTION, 1L, JAVAJIGI),
                        tuple(ANSWER, 2L, JAVAJIGI),
                        tuple(ANSWER, 3L, JAVAJIGI)
                );
    }
}
