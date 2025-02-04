//@@author jessteoxizhi

package gazeeebo.commands.places;

import gazeeebo.ui.Ui;

import java.io.IOException;
import java.util.Map;
import java.util.Stack;

public class AddPlacesCommand {
    /**
     * This method allows add a new place.
     *
     * @param ui      the object that deals with printing things to the user.
     * @param places  Map each place to a location
     * @param oldplaces Stack of previous places
     * @throws IOException catch any error if read file fails
     */
    public AddPlacesCommand(final Ui ui, final Map<String, String> places, Stack<Map<String, String>> oldplaces) {
        try {
            String room;
            String location;
            if (ui.fullCommand.equals("1")
                    || ui.fullCommand.trim().equals("add")
                    || ui.fullCommand.trim().equals("add-")) {
                System.out.println("Input the place you want "
                        + "to add in this format: Room,Location");
                ui.readCommand();
                String[] splitInfo = ui.fullCommand.split(",");
                room = splitInfo[0];
                location = splitInfo[1];
            } else {
                String[] parseInput = ui.fullCommand.split("-");
                String[] splitInfo = parseInput[1].split(",");
                room = splitInfo[0];
                location = splitInfo[1];
            }
            places.put(room, location);
            System.out.println("Successfully added :" + room + "," + location);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input add command in the correct format");
            oldplaces.pop();
        }
    }
}
