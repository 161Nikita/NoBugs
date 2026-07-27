package homework_13.adapter_pdf_doc;

public class Main {
    public static void main(String[] args) {
        Doc doc = new WorkDoc();
        doc.doc("docfile.doc");
        PdfFile pdfFile = new PdfFile();
        Doc adapter = new PdfToDocAdapter(pdfFile);
        adapter.doc("adapterInPdf.pdf");
    }


}
