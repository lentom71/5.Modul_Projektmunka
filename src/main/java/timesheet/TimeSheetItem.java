package timesheet;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSheetItem
{
    Employee employee;
    Project project;
    LocalDateTime beginDate;
    LocalDateTime endDate;

    public TimeSheetItem(Employee employee, Project project, LocalDateTime beginDate, LocalDateTime endDate)
    {
        this.employee = this.employee;
        this.project = this.project;
        if(beginDate.isAfter(endDate)  &&  beginDate.toLocalDate().equals(endDate.toLocalDate()))
        {
            this.beginDate = this.beginDate;
            this.endDate = this.endDate;
        }
        else
        {
            throw new IllegalArgumentException("Rossz dátum");
        }
    }

    public long hoursBetweenDates()
    {
        return  Duration.between(beginDate, endDate).toHours();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
