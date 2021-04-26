import com.github.javafaker.Faker;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class   SabanaResearchTest {

    private SabanaResearch sabanaResearch;
    private final List<Group> groups;
    private final List<Project> projects;



    private static Faker faker;


    public SabanaResearchTest() {
        this.groups = new ArrayList<>();
        this.projects = new ArrayList<>();
        faker = new Faker(new Locale("en-US"));

    }

    @BeforeEach
    public void setup() {
        Project wellFormedProject;
        Project wellFormedProject1;
        Group group = new Group(faker.team().name());
        groups.add(group);

        wellFormedProject = new Project(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        wellFormedProject1 = new Project(LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), group);
        projects.add(wellFormedProject);
        projects.add(wellFormedProject1);

        Iteration iteration = new Iteration(faker.team().name(), wellFormedProject);
        Iteration iteration1 = new Iteration(faker.team().name(), wellFormedProject1);


        // Create a Documented Activity
        NormalActivity activity1 = new NormalActivity(Activity.ACTIVE_STATE, iteration1);
        activity1.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity1 = new DocumentedActivity(Activity.ACTIVE_STATE, iteration, activity1);
        documentedActivity1.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(Activity.ACTIVE_STATE, iteration);
        activity1.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));



        sabanaResearch = new SabanaResearch(groups);
    }

    @Test
    @DisplayName("GIVEN sabana research WHEN create summary THEN a new summary is created")
    public void shouldCreateSummary() {
        Summary summary = sabanaResearch.createSummaryEntry();

        assertNotNull(summary, "The summary should be created.");
        assertNotNull(summary.getDate(), "Validate summary date.");
        assertEquals(1, summary.getActiveProjects(), "Validate number of active projects");
        assertEquals(1, sabanaResearch.countOfSummaries(), "The default count of summaries");
    }

    @Test
    @DisplayName("GIVEN sabana research WHEN open a project by dates and create summary THEN a new summary is created")
    public void shouldCreateSummaryForOpenProjects() {

        this.projects.get(1).setDateEnd(LocalDate.now().plusDays(1));
        Summary summary = sabanaResearch.createSummaryEntry();

        assertNotNull(summary, "The summary should be created.");
        assertNotNull(summary.getDate(), "Validate summary date.");
        assertEquals(2, summary.getActiveProjects(), "Validate number of active projects");
        assertEquals(1, sabanaResearch.countOfSummaries(), "The default count of summaries");
    }
    @Test
    public void shouldCountClosedActivities() {

        Summary summary = sabanaResearch.createSummaryEntry();
        assertEquals(0, summary.getClosedActivities(), "The default count of summaries");
    }
    @Test
    public void shouldCountOpenActivities() {

        Summary summary = sabanaResearch.createSummaryEntry();
        assertEquals(4, summary.getOpenActivities(), "The default count of summaries");
    }

}
