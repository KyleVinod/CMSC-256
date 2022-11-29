package cmsc256;

public class Dog implements Comparable<Dog>{
    private String dogName;
    private int count;
    public Dog() {
        dogName = "";
        count = 0;
    }
    public Dog(String dogName, int count) {
        this.dogName = dogName;
        this.count = count;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(Dog o) {
        int compare = dogName.compareTo(o.dogName);
        return compare;
    }
}
