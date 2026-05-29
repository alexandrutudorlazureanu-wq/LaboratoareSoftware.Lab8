package ro.ulbs.proiectaresoftware.lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FIsierExcel2 {


    public static void citesteExcel (String numeFisier) {


        try (FileInputStream fisier = new FileInputStream(new File(numeFisier));



             Workbook workbook = new XSSFWorkbook(fisier)) {



            Sheet sheet = workbook.getSheetAt(0);


            for (Row row : sheet) {

                for (Cell cell : row) {

                    if (cell.getCellType() == CellType.NUMERIC) {

                        System.out.print(cell.getNumericCellValue() + "\t");

                    } else if (cell.getCellType() == CellType.STRING) {


                        System.out.print(cell.getStringCellValue() + "\t");


                    } else if (cell.getCellType() == CellType.FORMULA) {

                        System.out.print(cell.getNumericCellValue() + "\t");


                    }

                    System.out.print(" | ");


                }
                System.out.println();
            }

        } catch (Exception e) {


            System.out.println("Eroare la citire: " + e.getMessage());


        }
    }


    public static void copiaza(String fisierSursa, String fisierDestinatie) {


        try (FileInputStream fisierIn = new FileInputStream(new File(fisierSursa));

             Workbook workbook = new XSSFWorkbook(fisierIn)) {


            Sheet sheet = workbook.getSheetAt(0);


            for (Row row : sheet) {


                int celula = row.getLastCellNum();





                if (row.getRowNum() == 0) {

                    Cell headerNou = row.createCell(celula);

                    headerNou.setCellValue("Medie ");
                    continue;
                }

                double suma = 0;

                int contoarColoane = 0;


                for (int i = celula - 1; i >= 0 && contoarColoane < 3; i--) {


                    Cell cell = row.getCell(i);


                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {


                        suma += cell.getNumericCellValue();
                        contoarColoane++;


                    }
                }

                Cell celulaMedie = row.createCell( celula );
                if (contoarColoane == 3) {


                    celulaMedie.setCellValue(suma / 3.0);


                } else {


                    celulaMedie.setCellValue("   ");

                }
            }


            try (FileOutputStream fisierOut = new FileOutputStream(new File(fisierDestinatie))) {
                workbook.write(fisierOut);
                System.out.println(fisierDestinatie);
            }

        } catch (Exception e) {
            System.out.println("Eroare : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void copiazaFormula(String fisierSursa, String fisierDestinatie) {
        try (FileInputStream fisierIn = new FileInputStream(new File(fisierSursa));


             Workbook workbook = new XSSFWorkbook(fisierIn)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                int celulaIn = row.getLastCellNum();




                if (row.getRowNum() == 0) {


                    Cell headerNou = row.createCell(celulaIn);


                    headerNou.setCellValue("Medie cu Formula ");
                    continue;


                }

                int numarRowExcel = row.getRowNum() + 1;


                String formula = "AVERAGE(D" + numarRowExcel + ":F" + numarRowExcel + ")";

                Cell celulaFormula = row.createCell(celulaIn);

                celulaFormula.setCellFormula(formula);

            }


            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            evaluator.evaluateAll();

            try (FileOutputStream fisierOut = new FileOutputStream(new File(fisierDestinatie))) {

                workbook.write(fisierOut);

                System.out.println("Generat: " + fisierDestinatie);

            }

        } catch (Exception e) {

            System.out.println("Eroare: " + e.getMessage());

        }
    }
}





