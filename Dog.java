public class Dog extends Animal {
    public static void main(String[] args) {
        Dog o = new Dog();
        o.setType("lab");
        o.setSound("Bow");
        System.out.println("My Dog type is: "+o.getType());
        System.out.println("My Dog Sounds: "+o.getSound());
    }
}
