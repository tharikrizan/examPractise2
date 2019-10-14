package database;


import android.provider.BaseColumns;

public final class EmployeeClassInfo {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private EmployeeClassInfo() {}

    /* Inner class that defines the table contents */
    public static class EmployeeInfo implements BaseColumns {
        public static final String TABLE_NAME = "EmployeeInfo";
        public static final String COLUMN_NAME = "empName";
        public static final String COLUMN_TELEPHONE = "empTelephone";
        public static final String COLUMN_EMAIL = "empEmail";
        public static final String COLUMN_GENDER = "empGender";
        public static final String COLUMN_Type = "empType";


    }
}