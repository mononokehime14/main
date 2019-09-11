<<<<<<< HEAD
import UI.Ui;
=======
<<<<<<< HEAD
import Storage.Storage;
>>>>>>> f549e283c42c289d7c7324cee3f0e138922257cc
=======
>>>>>>> f7938ba1adc707a6bdf34ebdb286314c7a8a91d1
>>>>>>> Jason

import java.io.*;
import java.text.ParseException;
import java.util.List;



public class Duke {
    /**
     * Returns void main function for duke.
     *
     * @param args
     * @return Void.
     * @throws DukeException | ParseException | IOException | NullPointerException
     */
    public static void main(String[] args) {

        List<Task> list;
        Storage store = new Storage();
        boolean isExit = false;
        Ui ui = new Ui();
        ui.showWelcome();
        try {
            list = store.Readfile();
            while(!isExit) {
                ui.ReadCommand();
                String command = ui.FullCommand.split(" ")[0];
                Command c = Parser.parse(command);
                c.execute(list,ui, store);
                isExit = c.isExit();
            }
        }
        catch (DukeException | ParseException | IOException | NullPointerException e){
            if(e instanceof ParseException){
                ui.showDateFormatError();
            }
            else if (e instanceof IOException){
                ui.showIOErrorMessage(e);
            }
            else{
                ui.showErrorMessage(e);
            }
        }
        finally{
            System.out.println("System exiting");
        }

//        finally{
//        level 9 branch
//            System.out.println("");
//        }
//        level 6
    }
}