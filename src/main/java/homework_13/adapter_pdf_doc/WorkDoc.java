package homework_13.adapter_pdf_doc;

public class WorkDoc implements Doc{
    @Override
    public void doc(String fileName) {
        System.out.println("[Doc]: Успех с doc файлом");
    }
}
