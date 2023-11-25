//package nextstep.qna.domain;
//
//import static nextstep.users.domain.NsUserTest.JAVAJIGI;
//
//import java.time.LocalDateTime;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class DeleteHistoryTest {
//
//    @Test
//    @DisplayName("질문이 삭제되면 댓글 삭제 기록을 남긴다.")
//    void test1() {
//        // given
//        Question question = new Question(JAVAJIGI, "title1", "contents1");
//        question.addAnswer(new Answer(JAVAJIGI, question, "댓글1"));
//        question.addAnswer(new Answer(JAVAJIGI, question, "댓글2"));
//
//        // when
//        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, 0L, question.getWriter(),
//                LocalDateTime.now());
//
//        // then
//    }
//
//
//    @Test
//    @DisplayName("질문이 삭제되면 질문 삭제 기록을 남긴다.")
//    void test2() {
//        // given
//
//        // when
//
//        // then
//    }
//}
