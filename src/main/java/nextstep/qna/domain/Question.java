package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.QUESTION;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {
    protected final static String DELETE_QUESTION_EXCEPTION = "질문을 삭제할 권한이 없습니다.";

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete(NsUser nsUser) {
        checkOwner(nsUser);
        deleteAnswers(nsUser);
        changeDeleteStatus(true);
    }

    private void changeDeleteStatus(boolean status) {
        this.deleted = status;
    }

    private void deleteAnswers(NsUser nsUser) {
        answers.deleteAnswers(nsUser);
    }

    private void checkOwner(NsUser nsUser) {
        if (!this.isOwner(nsUser)) {
            throw new CannotDeleteException(DELETE_QUESTION_EXCEPTION);
        }
    }

    public List<DeleteHistory> writeDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(QUESTION, this.id, this.writer));
        deleteHistories.addAll(answers.createAnswerDeleteHistory());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
