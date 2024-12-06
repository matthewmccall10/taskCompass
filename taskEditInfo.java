public class taskEditInfo {
    private final int taskID; //represents index within arrayList
    private final task.TaskType taskType; //used to determine which arrayList
    
    public taskEditInfo(int editID, task.TaskType editType) {
        this.taskID = editID;
        this.taskType = editType;
    }

    //GETTERS
    public int getEditID() {
        return taskID;
    }

    public task.TaskType getEditType() {
        return taskType;
    }
}
