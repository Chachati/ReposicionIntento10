package entities;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Project> projects;


    public Group(String name) {
        this.name = name;
        this.projects = new ArrayList<>();
    }



    public void addProject(Project plan) {
        this.projects.add(plan);
    }

    public int countActiveProjects(){

        int s=0;

        for(Project p: this.projects)
        {
            if(p.isActive())
            {
                s++;
            }


        }
        return s;
    }
    /**
     * Devuelve el numero de planes cerrados
     * @return d
     */
    public int CountOpenActivities()
    {
        int d=0;
        for (Project a: this.projects)
        {
            d+=a.countOpenActivities();
        }

        return d;
    }
    /**
     * Devuelve el numero de planes cerrados
     * @return d
     */
    public int CountClosedActivities()
    {
        int d=0;
        for (Project a: this.projects)
        {
            d+=a.countClosedActivities();
        }

        return d;
    }


}
