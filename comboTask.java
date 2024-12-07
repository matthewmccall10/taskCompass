import java.time.LocalDate;

public class comboTask extends task {
    private String comboRepeatInterval; // E.g., daily, weekly, monthly
    private LocalDate comboEndDate; // Repetition end date
    private String comboPartnerName;
    
    // Constructor
    public comboTask(int taskID, String taskName, String userName, String description, String priority, boolean status, TaskType type, String repeatInterval, LocalDate endDate, String taskPartner) {
        super(taskID, taskName, userName, description, priority, status, type);
        this.comboRepeatInterval = repeatInterval;
        this.comboEndDate = endDate;
        this.comboPartnerName = taskPartner;
    }

    // Getters
    public String getRepeatInterval() {
        return comboRepeatInterval;
    }

    public LocalDate getEndDate() {
        return comboEndDate;
    }

    public String getPartnerName() {
        return comboPartnerName;
    }

    //Setters
    public void setRepeatInterval(String repeatInterval) {
        this.comboRepeatInterval = repeatInterval;
    }

    public void setEndDate(LocalDate endDate) {
        this.comboEndDate = endDate;
    }

    public void setPartnerName(String partnerName) {
        this.comboPartnerName = partnerName;
    }
}
