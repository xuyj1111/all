package xu.all.designPattern.filterPattern.abstr;

import xu.all.designPattern.filterPattern.entity.Person;

import java.util.List;

public interface Criteria {

    public List<Person> meetCriteria(List<Person> persons);
}
