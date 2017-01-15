package be.jegoossens.es.graph.poc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Document(indexName = "users", type = "user")
public class User {

    @Id
    private String id;
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private int age;
    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String gender;
    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private List<String> hobbies;
    @Field(type = FieldType.Date)
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
