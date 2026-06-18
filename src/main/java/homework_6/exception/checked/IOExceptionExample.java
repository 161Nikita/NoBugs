package homework_6.exception.checked;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IOExceptionExample {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("data.txt"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);}
            } catch (FileNotFoundException e){
                System.out.println("Файл не найден");
            } finally {
            try { if(reader != null) {
                reader.close();
            }
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка при закрытии файла " + e.getMessage());
            }
        }
    }
}

