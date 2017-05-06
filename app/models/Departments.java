package models;

import play.data.validation.Constraints;
public class Departments {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(100)
    public String Name;
    @Constraints.Required
    public double Salary;
    @Constraints.Required
    public int Leader;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public int getLeader() {
        return Leader;
    }

    public void setLeader(int leader) {
        Leader = leader;
    }
}
