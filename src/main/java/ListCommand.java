<<<<<<< HEAD
import UI.Ui;
=======
<<<<<<< HEAD
import Storage.Storage;
>>>>>>> f549e283c42c289d7c7324cee3f0e138922257cc
=======
>>>>>>> f7938ba1adc707a6bdf34ebdb286314c7a8a91d1
>>>>>>> Jason

import java.text.ParseException;
import java.util.List;

public class ListCommand extends Command {

    @Override
    public void execute(List<Task> list, Ui ui, Storage storage) throws DukeException, ParseException, NullPointerException  {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).listformat());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
