package gazeeebo.commands.tasks;

import gazeeebo.commands.Command;
import gazeeebo.tasks.Task;
import gazeeebo.TriviaManager.TriviaManager;
import gazeeebo.UI.Ui;
import gazeeebo.storage.Storage;

import java.io.IOException;

import gazeeebo.tasks.*;
import gazeeebo.exception.DukeException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;


public class TimeboundCommand extends Command {
    @Override
    public void execute(ArrayList<Task> list, Ui ui, Storage storage, Stack<ArrayList<Task>> commandStack, ArrayList<Task> deletedTask, TriviaManager triviaManager) throws DukeException, ParseException, IOException {
        String description = "";
        String duration = ui.fullCommand.split("/")[1];
        try {
            if (duration.length() > 6 && duration.length() < 33) {
                throw new DukeException("OOPS!!! There is no proper duration of time allocated for this task.");
            } else {
                description = ui.fullCommand.split("/between ")[0];
            }
            String period = ui.fullCommand.split("/between ")[1];

            Timebound tb = new Timebound(description, period);
            list.add(tb);
            System.out.println("Got it. I've added this task:");
            System.out.println(tb.listFormat());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).toString() + "\n");
            }
            storage.writeToSaveFile(sb.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Program does not exit and continues running
     * since command "bye" is not called.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}