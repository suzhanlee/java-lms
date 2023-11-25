package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.users.domain.NsUser;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
    }

    public List<DeleteHistory> createAnswerDeleteHistory() {
        return answers
                .stream()
                .map(Answer::writeDeleteHistory)
                .collect(Collectors.toList());
    }

    public void deleteAnswers(NsUser nsUser) {
        answers.forEach(answer -> answer.delete(nsUser));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
