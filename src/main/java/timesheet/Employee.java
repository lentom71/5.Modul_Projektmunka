package timesheet;

public class Employee
{
    String firstname;
    String lastname;
    String name;

    public Employee(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
     //   name = firstname + lastname;
        return name;
    }
}

