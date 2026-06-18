package homework_5.hash_map;

public class Main {
    public static void main(String[] args) {

        HashMapTask hashMap = new HashMapTask();

        hashMap.addRecord("Nikita", 33);
        hashMap.addRecord("Vika", 13);
        hashMap.addRecord("Stepa", 41);
        hashMap.addRecord("Vitalya", 17);
        hashMap.addRecord("Anton", 37);
        hashMap.addRecord("ggg", 123);

       // hashMap.printAllRecords();

        hashMap.printUserUnder18();

    }
}
