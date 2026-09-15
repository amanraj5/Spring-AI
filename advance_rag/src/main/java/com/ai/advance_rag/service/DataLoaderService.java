package com.ai.advance_rag.service;

import com.ai.advance_rag.interfaces.InDataLoader;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderService implements InDataLoader {

    @Value("classpath:sample_data.json")
    private Resource jsonResource;

    @Value("classpath:sample_data.json")
    private Resource pdfResource;


    @Override
    public List<Document> loadDocumentFromJson() {
        JsonReader jsonReader = new JsonReader(jsonResource);
        return jsonReader.read();
    }

    @Override
    public List<Document> loadDocumentFromPdf() {
        PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(pdfResource,
                PdfDocumentReaderConfig.builder()
                        .withPageTopMargin(0)
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfTopTextLinesToDelete(0)
                                .build())
                        .withPagesPerDocument(1)
                        .build());

        return pdfDocumentReader.read();
    }
}
