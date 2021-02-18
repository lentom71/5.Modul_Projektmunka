package timesheet;

import java.util.HashMap;
import java.util.Map;

public class RiportLine
{
    public Project getProject() {
        return project;
    }

    Project project;

    public long getTime() {
        return time;
    }

    long time;

    public RiportLine(Project project, long time)
    {
        this.project = project;
        this.time = time;
    }

    public void addTime(int  num)
    {
        this.time = this.time  + num;
    }
}
