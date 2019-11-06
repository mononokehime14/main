package capCalculatorTest;

import gazeeebo.UI.Ui;
import gazeeebo.commands.capCalculator.CAPCommand;
import gazeeebo.commands.capCalculator.ListCAPCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCAPCommandTest {
    private static final String LINEBREAK = "------------------------------\n";
    private Ui ui = new Ui();
    private HashMap<String, ArrayList<CAPCommand>> map = new HashMap<>();
    private Map<String, ArrayList<CAPCommand>> CAPList = new TreeMap<>(map);

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream mine = new PrintStream(output);
    private PrintStream original = System.out;

    @BeforeEach
    void setupStream() {
        System.setOut(mine);
    }

    @AfterEach
    void restoreStream() {
        System.out.flush();
        System.setOut(original);
    }

    @Test
    void testListAllCAPCommand() throws IOException {
        CAPCommand newCAP = new CAPCommand("CS1231", 4, "A");
        ArrayList<CAPCommand> list = new ArrayList<>();
        list.add(newCAP);
        ArrayList<CAPCommand> list2 = new ArrayList<>();
        CAPCommand newCAP2 = new CAPCommand("CG1112", 6, "A");
        list2.add(newCAP2);
        CAPList.put("1", list);
        CAPList.put("2", list2);
        ui.fullCommand = "list all";
        ListCAPCommand test = new ListCAPCommand(ui, CAPList, LINEBREAK);
        assertEquals("Sem | Module code | MC | CAP\n" + LINEBREAK
                + "1   | CS1231      | 4  | A\n" + LINEBREAK
                + "2   | CG1112      | 6  | A\n"
                + LINEBREAK
                + "Total CAP: 5.0\n", output.toString());
    }

    @Test
    void testListSemFoundCAPCommand() throws IOException {
        CAPCommand newCAP = new CAPCommand("CS1231", 4, "A");
        ArrayList<CAPCommand> list = new ArrayList<>();
        list.add(newCAP);
        ArrayList<CAPCommand> list2 = new ArrayList<>();
        CAPCommand newCAP2 = new CAPCommand("CG1112", 6, "A");
        list2.add(newCAP2);
        CAPList.put("1", list);
        CAPList.put("2", list2);
        ui.fullCommand = "list 1";
        ListCAPCommand test = new ListCAPCommand(ui, CAPList, LINEBREAK);
        assertEquals("Sem | Module code | MC | CAP\n" + LINEBREAK
                + "1   | CS1231      | 4  | A\n"
                + LINEBREAK
                + "Sem 1 CAP: 5.0\n", output.toString());
    }

    @Test
    void testListSemFoundEmptyCAPCommand() throws IOException {
        CAPCommand newCAP = new CAPCommand("CS1231", 4, "A");
        ArrayList<CAPCommand> list = new ArrayList<>();
        list.add(newCAP);
        ArrayList<CAPCommand> list2 = new ArrayList<>();
        CAPCommand newCAP2 = new CAPCommand("CG1112", 6, "A");
        list2.add(newCAP2);
        CAPList.put("1", list);
        CAPList.put("2", list2);
        ui.fullCommand = "list 3";
        ListCAPCommand test = new ListCAPCommand(ui, CAPList, LINEBREAK);
        assertEquals("Sem | Module code | MC | CAP\n" + LINEBREAK
                + "No modules in this semester!\n", output.toString());
    }

    @Test
    void testListSemNotFoundCAPCommand() throws IOException {
        CAPCommand newCAP = new CAPCommand("CS1231", 4, "A");
        ArrayList<CAPCommand> list = new ArrayList<>();
        list.add(newCAP);
        ArrayList<CAPCommand> list2 = new ArrayList<>();
        CAPCommand newCAP2 = new CAPCommand("CG1112", 6, "A");
        list2.add(newCAP2);
        CAPList.put("1", list);
        CAPList.put("2", list2);
        ui.fullCommand = "list 1 and 2";
        ListCAPCommand test = new ListCAPCommand(ui, CAPList, LINEBREAK);
        assertEquals("Please Input in the correct format\n", output.toString());
    }

    @Test
    void testListNonsenseCAPCommand() throws IOException {
        CAPCommand newCAP = new CAPCommand("CS1231", 4, "A");
        ArrayList<CAPCommand> list = new ArrayList<>();
        list.add(newCAP);
        ArrayList<CAPCommand> list2 = new ArrayList<>();
        CAPCommand newCAP2 = new CAPCommand("CG1112", 6, "A");
        list2.add(newCAP2);
        CAPList.put("1", list);
        CAPList.put("2", list2);
        ui.fullCommand = "list nonsense";
        ListCAPCommand test = new ListCAPCommand(ui, CAPList, LINEBREAK);
        assertEquals("Please Input in the correct format\n", output.toString());
    }
}
