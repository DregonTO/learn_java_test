package learn.java.reflect;

public class TargetObject {
    private String value;

    public TargetObject() {
        this.value = "构造方法赋值value";
    }

    public void publicMethod(String s) {
        System.out.println("I love "+s);
    }

    private void privateMethod(){
        System.out.println("value is "+value);
    }
}
