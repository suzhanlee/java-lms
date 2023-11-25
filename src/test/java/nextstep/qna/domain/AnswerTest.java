package nextstep.qna.domain;

import static nextstep.qna.domain.Answer.DELETE_ANSWER_EXCEPTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답글의 상태를 답글 작성자와 사용자가 같을 때 삭제 상태로 바꾼다.")
    void delete_answer() {
        // when
        A1.delete(JAVAJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답글 작성자와 사용자가 다르면 예외를 던진다.")
    void delete_answer_exception() {
        // when // then
        assertThatThrownBy(() -> A1.delete(SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage(DELETE_ANSWER_EXCEPTION);
    }

    @Test
    @DisplayName("deleteHistory 목록을 생성합니다.")
    void test(){
        // given
        // when
        DeleteHistory deleteHistory = A1.writeDeleteHistory();
        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI));
    }
}
