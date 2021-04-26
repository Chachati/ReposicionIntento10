import com.github.javafaker.Faker;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

    class StudentSysthesizerTest {

    private static Faker faker;

    private Project wellFormedProject;

    public StudentSysthesizerTest() {

        faker = new Faker(new Locale("en-US"));
    }

    @BeforeEach
    public void setup() {
        setupWellFormedProject();
    }

    @Test
    void MakethesummarizeStudent()
    {
        StudentSynthesizer s = new StudentSynthesizer();
        assertTrue(s.synthetizerProject(wellFormedProject).contains("Pepe, 10"));
    }
    private void setupWellFormedProject() {

        Group group = new Group(faker.team().name());
        wellFormedProject = new Project(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration("Protect", wellFormedProject);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));
        Student student = new Student("Pepe",Duration.ofDays(10));
        wellFormedProject.getMembers().add(student);
    }
}
