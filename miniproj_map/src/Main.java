import view.Menu;
import view.View;

/*
controller classes including:
DataHandler.java to fetch data from initial files and store in final files and map
SearchHandler.java to search data in
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {

            // def var
            Scanner sc = new Scanner(System.in);
            View v = new View(1);
            Menu m = new Menu();

            boolean end = false;
            boolean prt = false;

            m.printMenu();
            while (!end) {

                try {
                    // match cmd
                    switch (sc.nextInt()) {

                        // basic search
                        case 1 -> {
                            if(prt) {
                                v = new View(1);
                                prt = false;
                            }
                            sc.nextLine();
                            v.bscSearch(sc.nextLine());
                        }

                        // prioritised search
                        case 2 -> {
                            if(!prt) {
                                v=new View(2);
                                prt = true;
                            }
                            sc.nextLine();
                            v.prtSearch(sc.nextLine());
                        }

                        // end
                        case 3 -> end = true;
                    }
                } catch (Exception e) {
                    System.out.println("sth went wrong");
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

