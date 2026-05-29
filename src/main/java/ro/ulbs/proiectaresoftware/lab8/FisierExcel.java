package ro.ulbs.proiectaresoftware.lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class FisierExcel {

    public static void main(String[] args) {

        String numeFisierExcel = "laborator8_input.xlsx";

        try (FileInputStream fisier = new FileInputStream(new File(numeFisierExcel));
             Workbook workbook = new XSSFWorkbook(fisier)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {



                        System.out.print(cell.getNumericCellValue() + "    ");



                    } else if (cell.getCellType() == CellType.STRING) {


                        System.out.print(cell.getStringCellValue() + "    ");



                    }



                    System.out.print(" | ");
                }


                System.out.println();


            }

        }
        catch (Exception e) {

            System.out.println("Eroare:  " + e.getMessage());

            e.printStackTrace();
        }


        System.out.println( " Primul fisier ");
        FIsierExcel2.citesteExcel(numeFisierExcel);


        System.out.println("Al doilea fisier ");
        FIsierExcel2.copiaza(numeFisierExcel , "laborator8_output2.xlsx");

        System.out.println();
        FIsierExcel2.citesteExcel("laborator8_output2.xlsx");


        System.out.println("Al treilea fisier");
        FIsierExcel2.copiazaFormula(numeFisierExcel, "laborator8_output3.xlsx");
        FIsierExcel2.citesteExcel("laborator8_output3.xlsx");
    }
}