package com.larissa.apigraphqltrainee.pdfa;

import com.larissa.apigraphqltrainee.model.Car;
import org.apache.jempbox.xmp.XMPMetadata;
import org.apache.jempbox.xmp.pdfa.XMPSchemaPDFAId;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class CreatePDFA {

        public static void createPDF(Car car) throws Exception{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont fonte = PDType1Font.TIMES_ROMAN;

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(fonte, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Dados do veículo");
            contentStream.newLine();
            contentStream.showText("Nome: " + car.getName());
            contentStream.newLine();
            contentStream.showText("Placa: " + car.getLicensePlate());
            contentStream.endText();
            contentStream.close();

            createMetaDataPDFA(doc, car);



            doc.save("/app/arquivo.pdf");
            doc.close();

        }

        public static void createMetaDataPDFA(PDDocument doc, Car car){

            PDDocumentInformation pdDocumentInformation = doc.getDocumentInformation();
            pdDocumentInformation.setAuthor("LARISSA SILVA");
            pdDocumentInformation.setTitle("RELATÓRIO CARRO");

            Calendar data = Calendar.getInstance();
            pdDocumentInformation.setCreationDate(data);
            pdDocumentInformation.setModificationDate(data);

            pdDocumentInformation.getCustomMetadataValue("Nome: " + car.getName());
            pdDocumentInformation.getCustomMetadataValue("Placa: " + car.getLicensePlate());

            PDMetadata metadata = new PDMetadata(doc);
            PDDocumentCatalog catalog = doc.getDocumentCatalog();
            catalog.setMetadata(metadata);

            metaDataXMP(metadata);
            ColorProfile(doc, catalog);
            readMetaData(metadata,doc,catalog);

        }

        public static void ColorProfile(PDDocument doc, PDDocumentCatalog catalog) {
            try {
                InputStream colorProfile = Files.newInputStream(Paths.get("/app/ICC/sRGB_IEC61966-2-1_black_scaled.icc"));

                PDOutputIntent outputIntent = new PDOutputIntent(doc, colorProfile);
                outputIntent.setInfo("sRGB IEC61966-2.1");
                outputIntent.setOutputCondition("sRGB IEC61966-2.1");
                outputIntent.setOutputConditionIdentifier("sRGB IEC61966-2.1");
                outputIntent.setRegistryName("http://www.color.org");
                catalog.addOutputIntent(outputIntent);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void metaDataXMP(PDMetadata metadata){
            try {
                XMPMetadata xmpMetadata = new XMPMetadata();
                XMPSchemaPDFAId pdfaId = new XMPSchemaPDFAId(xmpMetadata);
                xmpMetadata.addSchema(pdfaId);
                pdfaId.setConformance("B");
                pdfaId.setPart(1);
                pdfaId.setAbout("");
                metadata.importXMPMetadata(xmpMetadata.asByteArray());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        public static void readMetaData(PDMetadata metadata, PDDocument doc, PDDocumentCatalog catalog){
            doc.getDocumentCatalog();
            catalog.getMetadata();

            if(metadata == null){
                System.out.println("Sem metadata no documento");
                System.exit(1);
            }

            InputStream inputStream = null;
            try {
                inputStream = metadata.createInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            bufferedReader.lines().forEach(System.out::println);

        }

    }

