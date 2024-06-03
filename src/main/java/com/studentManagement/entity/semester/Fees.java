package com.studentManagement.entity.semester;
import com.studentManagement.entity.Student;
import jakarta.persistence.*;
@Entity
@Table(name ="fees")
public class Fees
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id1")
    private String id1;

    @Column(name="date")
    private String date;

    @Column(name="paid")
    private String paid;

    @Column(name="balance")
    private String balance;

    @Column(name="Total")
    private String Total;

    @Column(name="percentage")
    private String percentage;

    @Column(name="transactionId",unique = true)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "student-id", nullable = false)
    private Student fees;

    public Fees(Long id, String id1, String date, String paid, String balance, String total, String percentage, String transactionId, Student fees) {
        this.id = id;
        this.id1 = id1;
        this.date = date;
        this.paid = paid;
        this.balance = balance;
        Total = total;
        this.percentage = percentage;
        this.transactionId = transactionId;
        this.fees = fees;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    //    public Fees(Long id, String id1, String date, String paid, String balance, String transactionId, Student student) {
//        this.id = id;
//        this.id1 = id1;
//        this.date = date;
//        this.paid = paid;
//        this.balance = balance;
//        this.transactionId = transactionId;
//        this.fees = student;
//    }

    public Fees() {
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Student getFees() {
        return fees;
    }

    public void setFees(Student fees) {
        this.fees = fees;
    }
}
