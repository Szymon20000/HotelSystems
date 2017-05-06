package models;

import play.data.validation.Constraints;
public class Departments {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(100)
    public String name;
    @Constraints.Required
    public Double salary;
    @Constraints.Required
    public Integer leader;

    Departments() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }
}
