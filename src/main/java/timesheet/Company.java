package timesheet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class Company
{
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<TimeSheetItem> timeSheetItems = new ArrayList<>();


    public Company(InputStream employeesFile, InputStream projectsFile)
    {
        loadEmployees(employeesFile);
        loadProjects(projectsFile);
    }

    private void loadEmployees(InputStream file)
    {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            employees.add(new Employee(scanner.next(), scanner.next()));
        }
        scanner.close();
    }

    private void loadProjects(InputStream file)
    {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            projects.add(new Project(scanner.nextLine()));
        }
        scanner.close();
    }

    public void addTimeSheetItem(Employee employee, Project project, LocalDateTime beginDate, LocalDateTime endDate)
    {
        timeSheetItems.add(new TimeSheetItem(employee, project, beginDate, endDate));
    }

    public List<RiportLine> calculateProjectByNameYearMonth(String employeeName, int year, int month)
    {
        Map<Project, RiportLine>  reportsByProjects = new HashMap<>();

        for (Project project: projects)
        {
            reportsByProjects.put(project, new RiportLine(project, 0));
        }
        for(TimeSheetItem item: timeSheetItems)
        {
            if(item.employee.equals(employeeName) && (item.getBeginDate()).getYear() == year &&  (item.getBeginDate()).getMonth().equals(month))
            {
                reportsByProjects.get(item.getProject()).addTime((int) item.hoursBetweenDates());
            }
        }

        List<RiportLine>  lista  = new ArrayList<>(reportsByProjects.values());

        lista.sort(Comparator.comparing(l -> l.project.getName()));

        return  lista;
    }

    public void printToFile(String employeeName, int year, int month, Path file)
    {
        List<RiportLine> report = calculateProjectByNameYearMonth(employeeName, year, month);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            int sumTime = 0;
            StringBuilder sb = new StringBuilder(employeeName);
            sb.append("\t" + year);
            sb.append(String.format("-%02d", month));
            sb.append("\t<<sumTime>>\n");

            for (RiportLine reportLine : report) {
                if (reportLine.time != 0) {
                    sb.append(reportLine.project.getName());
                    sb.append("\t" + reportLine.time);
                    sb.append("\n");
                    sumTime += reportLine.time;
                }
            }

            writer.write(sb.toString().replace("<<sumTime>>", String.valueOf(sumTime)));

        } catch (IOException e)
        {
            throw new IllegalStateException("The file cannot write!", e);
        }
    }
}
