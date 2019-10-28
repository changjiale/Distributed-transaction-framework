package txTransaction.transactional;

import txTransaction.lock.TaskLock;

public class TxTransaction {

    private String groupId;

    private String transactionId;

    private TransactionType transactionType;

    private TaskLock taskLock;

    public TxTransaction(String groupId, String transactionId) {
        this.groupId = groupId;
        this.transactionId = transactionId;
        this.taskLock = new TaskLock();
    }

    public TxTransaction() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TaskLock getTaskLock() {
        return taskLock;
    }

    public void setTaskLock(TaskLock taskLock) {
        this.taskLock = taskLock;
    }
}
