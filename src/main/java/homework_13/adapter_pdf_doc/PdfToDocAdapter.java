package homework_13.adapter_pdf_doc;

public class PdfToDocAdapter implements Doc{
    private final PdfFile pdfFile;

    public PdfToDocAdapter(PdfFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    @Override
    public void doc(String fileName) {
        fileName.replace("pdf", "doc");
        System.out.println("Сконвертировали в doc");
    }
}
