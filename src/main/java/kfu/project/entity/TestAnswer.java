package kfu.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Nurislam on 13.07.2018.
 */
@Entity
@Table(name = "test_answers")
public class TestAnswer extends BogoClass {

    @NotNull
    private String text;

    private boolean correct;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

    public TestAnswer() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
