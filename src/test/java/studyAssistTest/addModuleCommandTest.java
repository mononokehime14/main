//@@author mononokehime14
package studyAssistTest;

import gazeeebo.commands.studyassist.StudyPlannerCommand;
import gazeeebo.commands.studyassist.AddModuleCommand;
import gazeeebo.exception.DukeException;
import gazeeebo.ui.Ui;
import gazeeebo.storage.Storage;
import gazeeebo.storage.StudyAssistPageStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class addModuleCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    void addModule_emptyException() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add";
        try {
            new AddModuleCommand().execute(StudyPlan,studyAssistPageStorage,ui,oldStudyPlan);
//            fail();
        } catch (DukeException e){
            assertEquals("Please follow the correct input format~",e.getMessage());
        }
    }

    @Test
    void addModule_wrongModuleException() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add CD1234 to 5";
        try {
            new AddModuleCommand().execute(StudyPlan,studyAssistPageStorage,ui,oldStudyPlan);
//            fail();
        } catch (DukeException e){
            assertEquals("We currently do not support this module.",e.getMessage());
        }
    }
    @Test
    void addModule_wrongSemesterException() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add CS2040C to 9";
        try {
            new AddModuleCommand().execute(StudyPlan,studyAssistPageStorage,ui,oldStudyPlan);
//            fail();
        } catch (DukeException | IOException e){
            assertEquals("Please input correct Semester number.",e.getMessage());
        }
    }
    @Test
    void addModule_wrongFormatException() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add CS2040C to";
        try {
            new AddModuleCommand().execute(StudyPlan,studyAssistPageStorage,ui,oldStudyPlan);
//            fail();
        } catch (DukeException | IOException e){
            assertEquals("Please follow the correct input format~",e.getMessage());
        }
    }
    @Test
    void addModule_duplicatedModuleException() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add CS2113T sem 5";
        try {
            new AddModuleCommand().execute(StudyPlan,studyAssistPageStorage,ui,oldStudyPlan);
//            fail();
        } catch (DukeException | IOException e){
            assertEquals("This module is already inside the study plan",e.getMessage());
        }
    }
    @Test
    void addModuleTest() throws IOException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        StudyAssistPageStorage studyAssistPageStorage = new StudyAssistPageStorage();
        StudyPlannerCommand StudyPlan = new StudyPlannerCommand(studyAssistPageStorage);
        Stack<ArrayList<ArrayList<String>>> oldStudyPlan = new Stack<>();
        oldStudyPlan.push(StudyPlan.StudyPlan);
        ui.fullCommand = "add CS2040C sem 5";
        String ModuleCode = "CS2040C";
        boolean flag = false;
        for(int i=0;i<StudyPlan.StudyPlan.size()&& !flag;i++){
            if(StudyPlan.StudyPlan.get(i).contains(ModuleCode)) flag = true;
        }
        if(flag){
            try {
                new AddModuleCommand().execute(StudyPlan, studyAssistPageStorage, ui, oldStudyPlan);
//            fail();
            } catch (DukeException | IOException e) {
                assertEquals("This module is already inside the study plan", e.getMessage());
            }
        }else {
            try {
                new AddModuleCommand().execute(StudyPlan, studyAssistPageStorage, ui, oldStudyPlan);
//            fail();
            } catch (DukeException | IOException e) {
                assertEquals("This module " + ModuleCode + " has been successfully added to Sem" + 5+".", e.getMessage());
            }
        }
    }
}
