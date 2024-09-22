package com.example.exame_app.controllers;

import com.example.exame_app.domain.Exame;
import com.example.exame_app.domain.Paciente;
import com.example.exame_app.services.PacienteService;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ExamePdfController {

  @Autowired
  PacienteService service;


   @GetMapping("")
   ResponseEntity<Document> getAll() throws FileNotFoundException, DocumentException {
        PdfDocument document = new PdfDocument();
		PdfWriter.getInstance(document, new FileOutputStream("exame.pdf"));

		document.open();
    Font font = FontFactory.getFont(
            FontFactory.COURIER, 16, BaseColor.BLACK);
    Chunk chunk = new Chunk(service.findAll().toString(), font);

		//document.add(chunk);
        document.add(new Paragraph(chunk));
		document.close();
       return ResponseEntity.ok().build();

   }
    @GetMapping("/{id}")
    ResponseEntity<Exame> getPdfId(@PathVariable Long id) throws IOException, DocumentException {
       //PdfDocument document = new PdfDocument();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("exameID.pdf"));
      //  PdfWriter.getInstance(document, new FileOutputStream("C:/Users/EliziaJanuarioDaSilv/Download/exame.pdf"));

        document.open();
        Optional<Paciente> obj = service.findById(id);
        document.addTitle("Exame médico");
        document.addSubject("Serviço de exame médico");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Elizia J da Silva");
        document.addCreator("Elizia Januario da Silva");

        /**cabeçalho*/
        /**titulo*/
        Font font = FontFactory.getFont(
                FontFactory.TIMES_BOLD, 22,BaseColor.BLACK);
        Font fontP = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
        Font fontPhrase = FontFactory.getFont(FontFactory.HELVETICA,13,Font.BOLD);
        Paragraph conteudo = new Paragraph();
        Chunk chunk = new Chunk("Exame de Ecocardiograma\n", font);
        conteudo.add(new Paragraph(" "));
        Phrase phrase = new Phrase();
        Paragraph conteudo2 = new Paragraph();
        conteudo.add(new Paragraph(" "));

        conteudo.add(chunk);
        conteudo.add(new Paragraph(" "));
        conteudo.add(new Paragraph("Nome: " + String.valueOf(obj.get().getNome()),fontP));
        conteudo.add(new Paragraph("Idade: " + String.valueOf(obj.get().getIdade()),fontP));
        conteudo.add(new Paragraph("Peso: " + String.valueOf(obj.get().getPeso()),fontP));
        conteudo.add(new Paragraph("Altura: " + String.valueOf(obj.get().getAltura()),fontP));
        conteudo.add(new Paragraph("Médico responsavel: Nome do médico responsavel " ));
        conteudo.add(new Paragraph("CRM: 5265987 " ));
        conteudo.add(new Paragraph(" "));
        conteudo.add(new Chunk("Medidas Númericas",fontTitulo));
        conteudo.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        //descrição
        PdfPCell c1 = new PdfPCell(new Phrase("Medidas",fontTitulo));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Valor",fontTitulo));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Valores Normais",fontTitulo));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

            //medidas
            c1 = new PdfPCell(new Phrase("AO"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(String.valueOf(obj.get().getExames())));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("AE"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAe()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("VID"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getVid()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("VED"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getVed()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("VES"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("SIV"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("pp"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("Fração de Ejeção"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("e"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("e'"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            //medidas
            c1 = new PdfPCell(new Phrase("Outra medida'"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("String.valueOf(obj.get().getAltura()"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("0-10"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

        // medidas descritivas



        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Medidas Descritivas ",fontTitulo));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Medidas Cavitárias: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Medidas do Ventrículo Esquerdo: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Função Sistólica: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Contratilidade Segmentar:: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Função Diastolica: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Cavidades Direita:: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Aorta: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Válvula Aórtica: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Válvula Mitral: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Tricúspide: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Válvula Pulmonar: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Pericárdio: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Cava: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));
        conteudo2.add(new Paragraph("Comentário: ",fontPhrase));
        conteudo2.add(new Paragraph("Medidas Cavitárias ",fontP));
        conteudo2.add(new Paragraph(" "));

























        /** Criação de um apontamento para um capítulo
        Anchor ancora = new Anchor("Capítulo 1", fontP);
        ancora.setName("Capitulo 1");

        // Capítulo do arquivo
        Chapter capitulo = new Chapter(new Paragraph(ancora), 1);

        Paragraph novoParagrafo = new Paragraph("Tabela de precos",
                fontP);

        // Seção é uma área que adicionaremos conteúdo
        Section secao = capitulo.addSection(novoParagrafo);*/




        document.add(conteudo);
        document.add(table);
        document.add(conteudo2);

        //document.add(secao);
        /*document.add(new Paragraph("", FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.BOLDITALIC, new Color(0, 0, 255),(String.valueOf(obj.get().
                        getNome()))));*/



       /** for (Exame item : obj.get().getExames()) {
        document.add(new Paragraph(String.valueOf(item.getAe())));
        }*/
        //Image figura = Image.getInstance("C:\\Users\\EliziaJanuarioDaSilv\\Download");
        //document.add(figura);

        document.close();
        return ResponseEntity.ok().build();
    }
}
