package com.example.cgeprogram

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.cgeprogram.desktopApp.App as CgeDesktopApp

// --- AÑADE TODAS ESTAS IMPORTACIONES ---
import java.io.File
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.font.Standard14Fonts

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CGE Electricidad",
    ) {
        CgeDesktopApp(
            onGuardarArchivo = { contenido, nombreArchivo ->
                try {
                    // crea un documento PDF vacío
                    val document = PDDocument()
                    // crea una página en blanco y la añade
                    val page = PDPage()
                    document.addPage(page)
                    // prepara un "lápiz" para escribir en la página
                    val contentStream = PDPageContentStream(document, page)
                    // configura la fuente y el tamaño
                    contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
                    // mueve el "lápiz" al inicio de la página (arriba a la izquierda)
                    contentStream.beginText()
                    contentStream.newLineAtOffset(50f, 700f) // (x: 50, y: 700)
                    contentStream.setLeading(14.5f) // Espacio entre líneas (interlineado)
                    // escribe cada línea de tu TEXTO en el PDF
                    contenido.split("\n").forEach { line ->
                        contentStream.showText(line)
                        contentStream.newLine() // Baja a la siguiente línea
                    }

                    contentStream.endText()
                    contentStream.close()

                    // guarda el documento en el archivo
                    document.save(File(nombreArchivo))
                    // cierra el documento
                    document.close()
                    println("PDF $nombreArchivo guardado con éxito.")

                } catch (e: Exception) {
                    println("Error al crear el PDF: ${e.message}")
                    // Si falla, lo guarda como TXT de respaldo
                    println("Guardando como .txt de respaldo...")
                    File(nombreArchivo.replace(".pdf", ".txt")).writeText(contenido)
                }
            }
        )
    }
}