package ro.ulbs.proiectaresoftware.lab7.forms;



public class MainContor {

    public static void main(String[] args) {

        Form f1 = new Form("Rosu");
        Form f2 = new Form("Albastru");
        Form f3 = new Form();

        System.out.println("Numar instante: " + Form.getInstanceCount());

    }

}
