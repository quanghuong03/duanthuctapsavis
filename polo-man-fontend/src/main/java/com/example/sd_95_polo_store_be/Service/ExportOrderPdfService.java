package com.example.sd_95_polo_store_be.Service;

import com.itextpdf.text.Document;
import jakarta.servlet.http.HttpServletResponse;

public interface ExportOrderPdfService {
Document OrderPdfExport(Integer id, HttpServletResponse response);
}
