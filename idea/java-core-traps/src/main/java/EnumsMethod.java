/**
 * Created by pixel on 4/3/15.
 */
public enum EnumsMethod {
    FELIX(2), SHEEBA(3), RUFUS(7);

    private int mAge;

    EnumsMethod(int age) {
        mAge = age;
    }

    public int getAge() {
        return mAge;
    }

    public static void main(String[] args) {
        System.out.println(EnumsMethod.FELIX.getAge());
        // enum implements interface https://github.com/peter-lawrey/Java-Thread-Affinity/blob/master/src/main/java/com/higherfrequencytrading/affinity/AffinityStrategies.java
    }
}
