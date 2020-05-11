package com.AMRA.x00366519;

import jdk.dynalink.linker.ConversionComparator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        /*ArrayList<PDF> archivos = new ArrayList<>();
        String texto;
         */
        String aux;
        Date fecha = null;
        int opcion;

        opcion = menu();

        switch (opcion){
            case 1:
                aux = JOptionPane.showInputDialog("Año a buscar: ");

                DateFormat formato = new SimpleDateFormat("yyyy");
                Calendar f = Calendar.getInstance();
                try {
                    fecha = formato.parse(aux);
                    f.setTime(fecha);
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                // IMPRESIÓN EN CONSOLA PARA VERIFICACIÓN "BORRAR"
                System.out.print(f.get(Calendar.YEAR));
                break;
            case 2:
                aux = JOptionPane.showInputDialog("Fecha a buscar MM/yyyy: ");

                DateFormat formato2 = new SimpleDateFormat("MM/yyyy");
                Calendar f2 = Calendar.getInstance();
                try {
                    fecha = formato2.parse(aux);
                    f2.setTime(fecha);
                } catch (ParseException ex) {
                    System.out.println(ex);
                }

                System.out.print(((f2.get(Calendar.MONTH) + 1) + "/" + f2.get(Calendar.YEAR))); // IMPRESIÓN EN CONSOLA PARA VERIFICACIÓN "BORRAR"
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida");
                break;
        }
        PDF leerPDF = new PDF();
        String directory = leerPDF.getDirectory();
        String label = JOptionPane.showInputDialog(null, "Busca:");
        leerPDF.readPDF(directory, label);
    }

    public static int menu() {
        int tipo = 0;

        tipo = Integer.parseInt(JOptionPane.showInputDialog("Tipo de busqueda: " + "\n1. Año\n2. Año y mes"));

        return tipo;
    }

    public static void lectura(){
        try {
            File file = new File("C:\\Users\\Usuario\\Documents\\DiarioOficial.pdf");
            PDDocument document = PDDocument.load(file);

            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
            System.out.println(text);

            //Closing the document
            document.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
