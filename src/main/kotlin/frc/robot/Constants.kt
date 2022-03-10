package frc.robot

public class Constants {

    //IDs for CAN motor controllers
    public static final val DRIVE_VICTOR_LEFT1: Int = 0
    public static final val DRIVE_VICTOR_LEFT2: Int = 2
    public static final val DRIVE_VICTOR_RIGHT1: Int = 1
    public static final val DRIVE_VICTOR_RIGHT2: Int = 3
    public static final val ARM_TALON_ID: Int = 4
    public static final val INTAKE_TALON_ID: Int = 5

    //IDs for controllers
    public static final val XBOX_CTRL_PORT: Int = 0
    public static final val LOGIJOY_PORT: Int = 1

    //IDs for limit switches
    public static final val UPLIMIT_ID: Int = 8
    public static final val DOWNLIMIT_ID: Int = 9

    //Motor speeds
    public static final val DRIVE_SPEED_MULTIPLIER: Double = 1.00
    public static final val INTAKE_MAX_SPEED_IN: Double = 0.45
    public static final val INTAKE_MAX_SPEED_OUT: Double = 0.70
    public static final val TWIST_DISCOUNT: Double = 0.60
    public static final val ARM_UP_SPEED: Double = 0.50
    public static final val ARM_DOWN_SPEED: Double = 0.30

}