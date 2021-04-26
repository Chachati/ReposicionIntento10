package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExecutiveSynthesizer implements ISynthesizer{

    @Override
    public List<String> synthetizerProject(Project p) {

        List<String> iterationsData = new ArrayList<>();

        for(Iteration s: p.getIterations())
        {
            String name = s.getGoal();
            Duration workedHours;
            try {
                 workedHours = s.getDuration();

            }
            catch (SabanaResearchException e)
            {
                workedHours = Duration.ofDays(0);
            }
            iterationsData.add(name+", "+workedHours.toDays());
        }
        return iterationsData;
    }
}
