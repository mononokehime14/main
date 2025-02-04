package gazeeebo.commands;

import gazeeebo.tasks.Task;
import gazeeebo.triviamanager.TriviaManager;
import gazeeebo.ui.Ui;
import gazeeebo.storage.Storage;
import gazeeebo.exception.DukeException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A base abstract class of all the gazeeebo.commands.
 */
public abstract class Command {

    public abstract void execute(ArrayList<Task> list,
                                 Ui ui, Storage storage,
                                 Stack<ArrayList<Task>> commandStack,
                                 ArrayList<Task> deletedTask,
                                 TriviaManager triviaManager)
            throws DukeException, ParseException,
            IOException, NullPointerException;

    public abstract boolean isExit();
}

