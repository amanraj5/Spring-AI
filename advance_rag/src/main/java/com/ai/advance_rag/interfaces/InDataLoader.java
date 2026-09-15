package com.ai.advance_rag.interfaces;

import org.springframework.ai.document.Document;

import java.util.List;

public interface InDataLoader {
    List<Document> loadDocumentFromJson();
    List<Document> loadDocumentFromPdf();
}
