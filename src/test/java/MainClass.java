public class MainClass {
    public int getLocalNumber() {
        return 14;
    }

    private int class_number() {
        return 20;
    }

    public int getClassNumber() {
        return this.class_number();
    }

    private String class_string() {
        String string = "Hello,world";
        return string;
    }

    public String getClassString() {
        return this.class_string();
    }
}
