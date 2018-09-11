package spring.boot.core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User Entity 
 * <p>
 * Created by bysocket on 21/07/2017.
 */
@Entity
public class User implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name
     */
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 8, message = "Name length should between 2 to 20")
    private String name;

    /**
     * Age
     */
    @NotNull(message = "Age can't be empty")
    @Min(value = 0, message = "age should  > 0")
    @Max(value = 300, message = "age should < 300")
    private Integer age;

    /**
     * Birthday
     */
    @NotEmpty(message = "Birthday can't be empty")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
