package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private XboxController controller;
  private static final int leftDeviceID = 2; 
  private static final int rightDeviceID = 6;
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;
  private CANSparkMax m_leftMotor1;
  private CANSparkMax m_rightMotor1;
  private MotorControllerGroup left;
  private MotorControllerGroup right;

  @Override
  public void robotInit() {
  /**
   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */
    m_leftMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_leftMotor1 = new CANSparkMax(4, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
    m_rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);

    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    m_leftMotor.restoreFactoryDefaults();
    m_rightMotor.restoreFactoryDefaults();
    
    m_leftMotor1.follow(m_leftMotor, true);
    m_rightMotor1.follow(m_rightMotor, true);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);

    controller = new XboxController(0);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(-controller.getRawAxis(5), controller.getRawAxis(1));
  }
}