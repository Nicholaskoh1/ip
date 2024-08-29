package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for the Task Class.
     *
     * @param description Description of the task. Marked as not done.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task Class. Could be marked as done or not done.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of whether the task is done or not.
     *
     * @return The string representation of the task status.
     */
    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * String representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task status.
     *
     * @return true if the task is done and false if the task is not done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the file string representation of the task to be stored in the task list text file.
     *
     * @return the file string representation of the task.
     */
    public String toFileString() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Returns the string representation of the given date and time. For events and deadlines.
     *
     * @param date Given date.
     * @param format Date format.
     * @return String representation of the given date and time.
     */
    public String getDateString(LocalDateTime date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Returns false.
     *
     * @param date Given date.
     * @return false.
     */
    public boolean isOnThisDate(LocalDate date) {
        return false;
    }
}
