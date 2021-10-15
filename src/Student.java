public class Student {
    private String name;
    private String code;
    private String classRoom;
    private String birthDay;

    @Override
    public String toString() {
        return "nameStudent = " + name + '\'' +
                ", codeStudent= " + code +
                ", classRoom= " + classRoom ;
    }

    public Student() {
    }

    public Student(String name, String code, String classRoom, String birthDay) {
        this.name = name;
        this.code = code;
        this.classRoom = classRoom;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
