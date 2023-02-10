package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(new File("C:\\Users\\allan\\full\\automacoes\\DesafioAPI\\src\\test\\java\\utils\\createUser.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

// extrair dados do arquivo JSON
        String apiNameValue = root.get("name").asText();
        String emailtValue = root.get("email").asText();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("evidencias.pdf"));
            document.open();
            document.add(new Paragraph("Evidências"));

            Paragraph title = new Paragraph("Relatório de Teste de API");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph executor = new Paragraph("Nome do Executor: " + apiNameValue);
            document.add(executor);

            Paragraph apiEmail = new Paragraph("Email da API: " + emailtValue);
            document.add(apiEmail);

            Paragraph apiUrl = new Paragraph("Endereço da API: " + "apiUrlValue");
            document.add(apiUrl);

            Paragraph result = new Paragraph("Resultado do Teste: " + "resultValue");
            document.add(result);

            Paragraph status = new Paragraph("Resultado do StatusCode: " + "resultStatuscode");
            document.add(status);

            PdfPTable table = new PdfPTable(2); // 2 colunas
            table.setWidthPercentage(100); // largura da tabela é 100% da página
            table.setSpacingBefore(10f); // espaço antes da tabela
            table.setSpacingAfter(10f); // espaço depois da tabela

// adicionar cabeçalho da tabela
            PdfPCell header1 = new PdfPCell(new Paragraph("Nome da API"));
            header1.setBorderColor(BaseColor.BLACK);
            header1.setPaddingLeft(10);
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header1.setVerticalAlignment(Element.ALIGN_CENTER);
            header1.setBackgroundColor(BaseColor.GRAY);
            table.addCell(header1);

            PdfPCell header2 = new PdfPCell(new Paragraph("Resultado do Teste"));
            header2.setBorderColor(BaseColor.BLACK);
            header2.setPaddingLeft(10);
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setVerticalAlignment(Element.ALIGN_CENTER);
            header2.setBackgroundColor(BaseColor.GRAY);
            table.addCell(header2);

// adicionar dados à tabela
            PdfPCell apiNameCell = new PdfPCell(new Paragraph("apiNameValue"));
            apiNameCell.setBorderColor(BaseColor.BLACK);
            apiNameCell.setPaddingLeft(10);
            apiNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            apiNameCell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(apiNameCell);

            PdfPCell resultCell = new PdfPCell(new Paragraph("resultValue"));
            resultCell.setBorderColor(BaseColor.BLACK);
            resultCell.setPaddingLeft(10);
            resultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            resultCell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(resultCell);

            document.add(table);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
