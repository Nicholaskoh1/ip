package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class ListCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
    }

    @Test
    public void execute_nonEmptyList() throws JeffException {
        new AddToDoCommand("todo borrow book").execute(tasks, storage);
        new AddDeadlineCommand("deadline return book /by 2024-08-30 18:00").execute(tasks, storage);
        new AddEventCommand("event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00")
                .execute(tasks, storage);

        String response = new ListCommand("list").execute(tasks, storage);

        assertEquals(" 1.[T][  ] borrow book\n"
                        + " 2.[D][  ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + " 3.[E][  ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n",
                response);
    }

    @Test
    public void execute_emptyList_throwException() throws JeffException {
        Command c = new ListCommand("list");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("List is empty!", exception.toString());
    }
}
