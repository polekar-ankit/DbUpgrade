package at.com.dbupgrade;

/**
 * Creted by User on 14-Jul-17
 */

public class Student {
    private int nId;
    private String sName;
    private int nRollNo = -1;

    public Student() {
    }

    @Override
    public String toString() {
        return sName;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getnRollNo() {
        return nRollNo;
    }

    public void setnRollNo(int nRollNo) {
        this.nRollNo = nRollNo;
    }
}
