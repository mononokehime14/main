package commands;


import Tasks.Task;
import UI.Ui;
import Tasks.*;
import Storage.Storage;
import Exception.DukeException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class TodoCommand extends Command {

    @Override
    public void execute(ArrayList<Task> list, Ui ui, Storage storage) throws DukeException, ParseException, IOException, NullPointerException {
        String description = "";
        description = ui.FullCommand.substring(5);
        Todo to = new Todo(description);
        list.add(to);
        System.out.println("Got it. I've added this task:");
        System.out.println(to.listFormat());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass().getName().equals("Tasks.Deadline")) {
                sb.append(list.get(i).toString() + "\n");
            } else if (list.get(i).getClass().getName().equals("Tasks.Event")) {
                sb.append(list.get(i).toString() + "\n");
            } else if (list.get(i).getClass().getName().equals("Tasks.FixedDuration")) {
                sb.append(list.get(i).toString() + "\n");
            } else if (list.get(i).getClass().getName().equals("Tasks.DoAfter")) {
                sb.append(list.get(i).toString() + "\n");
            } else if (list.get(i).getClass().getName().equals("Tasks.Timebound")) {
                sb.append(list.get(i).toString() + "\n");
            } else {
                sb.append(list.get(i).toString() + "\n");
            }
        }
        storage.Storages(sb.toString());
    }
}
