package frc.robot;

public class Constants {

    // Device CAN IDs
    public static final int DRIVE_VICTOR_LEFT1 = 0;
    public static final int DRIVE_VICTOR_LEFT2 = 2;
    public static final int DRIVE_VICTOR_RIGHT1 = 1;
    public static final int DRIVE_VICTOR_RIGHT2 = 3;
    public static final int ARM_TALON_ID = 4;
    public static final int INTAKE_TALON_ID = 5;
    public static final int NEO_ID = 4;

    // Control ports
    public static final int XBOX_CTRL_PORT = 0;
    public static final int LOGIJOY_PORT = 1;
    
     // Performance constants
    public static final double DRIVE_SPEED_MULTIPLIER = 1;   // to be verified
    public static final double INTAKE_MAX_SPEED_IN = 1;  
    public static final double INTAKE_MAX_SPEED_OUT = 0.8;  
    public static final double TWIST_DISCOUNT = 0.8;   
           // tested, o
    public static final double ARM_UP_SPEED = 0.450;             // to be tested on assembled arm
    public static final double ARM_DOWN_SPEED = 0.35;             // to be tested on assembled arm
    // public static final double ARM_UP_MAX_CYCLES = 1000;
    // public static final double ARM_DOWN_MAX_CYCLES = 1000;

    //limit port numbers
    public static final int UPLIMIT_ID = 8;
    public static final int DOWNLIMIT_ID = 9;
}