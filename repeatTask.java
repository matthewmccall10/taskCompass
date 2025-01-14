import java.time.LocalDate;

public class repeatTask extends task {
    private String repeatInterval; // E.g., daily, weekly, monthly
    private LocalDate endDate; // The date the repetition ends

    // Constructor
    public repeatTask(int taskID, String taskName, String userName, String description, String priority, boolean status, TaskType type, String repeatInterval, LocalDate endDate) {
        super(taskID, taskName, userName, description, priority, status, type);
        this.repeatInterval = repeatInterval;
        this.endDate = endDate;
    }

    // Calculate next occurrence
    public LocalDate getNextOccurrence(LocalDate currentDate) {
        switch (repeatInterval.toLowerCase()) {
            case "daily":
                return currentDate.plusDays(1);
            case "weekly":
                return currentDate.plusWeeks(1);
            case "monthly":
                return currentDate.plusMonths(1);
            default:
                throw new IllegalArgumentException("Invalid repeat interval");
        }
    }

    // Check if the task is still repeating
    public boolean isRepeating(LocalDate currentDate) {
        return endDate == null || currentDate.isBefore(endDate) || currentDate.isEqual(endDate);
    }

    // Getters
    public String getRepeatInterval() {
        return repeatInterval;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    //Setters
    public void setRepeatInterval(String repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

