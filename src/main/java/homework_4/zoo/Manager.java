package homework_4.zoo;

public class Manager {
private Animal book;
    public void demonstration() {
       this.book.demonstration();
    }

    public void addAnimal(Animal animal){
        this.book = animal;
        System.out.println("Животное: " + animal.getType() + " добавлено в зоопарк");
    }
}
