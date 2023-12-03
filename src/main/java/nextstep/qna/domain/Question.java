package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

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

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return writeDeleteHistories();
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private List<DeleteHistory> writeDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(writeDeleteQuestionHistory());
        deleteHistories.addAll(writeDeleteAnswersHistory());
        return deleteHistories;
    }

    private DeleteHistory writeDeleteQuestionHistory() {
        deleteQuestion();
        return createDeleteQuestionHistory();
    }

    private void deleteQuestion() {
        this.deleted = true;
    }

    private DeleteHistory createDeleteQuestionHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
    }

    private List<DeleteHistory> writeDeleteAnswersHistory() throws CannotDeleteException {
        List<DeleteHistory> deleteAnswersHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteAnswersHistories.add(writeDeleteAnswerHistory(answer));
        }
        return deleteAnswersHistories;
    }

    private DeleteHistory writeDeleteAnswerHistory(Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(this.writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return answer.writeDeleteAnswerHistory();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}