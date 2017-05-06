package models;

import play.data.validation.Constraints;

import java.math.BigDecimal;

public class Departments {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(100)
    public String name;
    @Constraints.Required
    public BigDecimal salary;
    @Constraints.Required
    public Integer leader;

    public Departments() {}

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }
}
