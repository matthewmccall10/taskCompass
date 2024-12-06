public class task {

    public int taskID;
    public String taskName;
    public String taskUser;
    public String taskDescription;
    // public Date TBD;
    public String taskPriority;
    public boolean taskStatus;

    public enum TaskType {
        BASE, REPEAT, PARTNER, COMBO
    }

    private final TaskType taskType;

    //More need to be added
    
    //Constructor
    public task (int nextID, String name, String user, String description, String priority, boolean status, TaskType type) {
        this.taskID = nextID;
        this.taskName = name;
        this.taskUser = user;
        this.taskDescription = description;
        // this.taskDate = date;
        this.taskPriority = priority;
        this.taskStatus = status;
        this.taskType = type;
    }

    //Getters
    public String getTaskName() {
        return taskName;
    }

    public String getTaskUser() {
        return taskUser;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public int getTaskID() {
        return taskID;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    //Setters

}
