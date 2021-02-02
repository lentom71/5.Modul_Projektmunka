package timesheet;

import java.util.HashMap;
import java.util.Map;

public class RiportLine
{
    Project project;
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
