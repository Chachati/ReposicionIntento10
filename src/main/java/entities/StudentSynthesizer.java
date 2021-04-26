package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StudentSynthesizer implements ISynthesizer{

    @Override
    public List<String> synthetizerProject(Project p) {

        List<String> studentsdata = new ArrayList<>();

        for(Student s: p.getMembers())
        {
            String name = s.getName();
            Duration workedHours = s.getWorkedHours();

            studentsdata.add(name+", "+ workedHours.toDays());
        }
            return studentsdata;
    }
}
