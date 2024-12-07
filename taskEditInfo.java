public class taskEditInfo {
    private final String taskName;
    private final int taskID; //represents index within arrayList
    private final task.TaskType taskType; //used to determine which arrayList
    
    public taskEditInfo(String editName, int editID, task.TaskType editType) {
        this.taskID = editID;
        this.taskType = editType;
        this.taskName = editName;
    }

    //GETTERS
    public int getEditID() {
        return taskID;
    }

    public task.TaskType getEditType() {
        return taskType;
    }

    public String getEditName() {
        return taskName;
    }
}
