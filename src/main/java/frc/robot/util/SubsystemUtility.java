package frc.robot.util;

import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import java.util.ArrayList;
import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.Logger;

/** */
public class SubsystemUtility {

  private Subsystem subsystem;

  private MotorData leadingMotor;

  private ArrayList<MotorData> followingMotors;

  /**
   * @param subsystemName
   */
  public SubsystemUtility(String subsystemName, RobotState robotState) {
    subsystem = new Subsystem(new SubsystemIOReal(leadingMotor, followingMotors));
    subsystem.setName(subsystemName);
  }

  /**
   * @param leadingMotorData
   */
  public void setLeadingMotor(MotorData leadingMotorData) {
    leadingMotor = leadingMotorData;
  }

  /**
   * @param followingMotorData
   */
  public void addFollowingMotor(MotorData followingMotorData) {
    followingMotors.add(followingMotorData);
  }

  public enum RobotState {
    REAL,
    SIM,
    REPLAY
    }
}

interface SubsystemIO {

  @AutoLog
  public static class SubsystemIOInputs {

    public String sysIdState = "None";
  }

  public default void updateInputs(SubsystemIOInputs inputs) {}

  public default void initializeMotors(
      MotorData leadingMotorData, ArrayList<MotorData> followingMotorData) {}

  public default void setPosition(double setGoalPosition) {}

  public default void setVoltage(double appliedVolts) {}
}

class SubsystemIOReal implements SubsystemIO {

  private TalonFX leadingMotor;
  private ArrayList<TalonFX> followingMotors;
  private double goalPosition;

  public SubsystemIOReal(MotorData leadingMotorData, ArrayList<MotorData> followingMotorData) {

    initializeMotors(leadingMotorData, followingMotorData);
  }

  public void updateInputs(SubsystemIOInputs inputs) {

  } 

  public void initializeMotors( MotorData leadingMotorData, ArrayList<MotorData> followingMotorData) {

    leadingMotor = new TalonFX(leadingMotorData.getMotorID());
    leadingMotor.getConfigurator().apply(leadingMotorData.getMotorConfig());

    followingMotors = new ArrayList<TalonFX>();

    for (int i = 0; i > followingMotorData.size(); ++i) {

      MotorData motorData = followingMotorData.get(i);

      followingMotors.add(new TalonFX(motorData.getMotorID()));
      followingMotors.get(i).getConfigurator().apply(motorData.getMotorConfig());

    }
  }

  public void setGoalPosition(double setGoalPosition) {
    goalPosition = setGoalPosition;
    leadingMotor.setControl(new MotionMagicVelocityVoltage(goalPosition));
  }

  public void setVoltage(double appliedVolts) {
    leadingMotor.setVoltage(appliedVolts);
  }

  private double getPosition() {
    return leadingMotor.getPosition().getValueAsDouble();
  }
}

class SubsystemIOSim implements SubsystemIO {
  
}

class Subsystem extends SubsystemBase {

  SubsystemIO io;
  private final SubsystemIOInputsAutoLogged inputs = new SubsystemIOInputsAutoLogged();
  private final SysIdRoutine sysID;

  public double sysIDRampRate = 0.0;
  public double sysIDStepValue = 0.0;
  public double sysIDTimeout = 0.0;

  public Subsystem(SubsystemIO io) {
    this.io = io;

    sysID =
        new SysIdRoutine(
            new SysIdRoutine.Config(
                Volts.of(sysIDRampRate).per(Second),
                Volts.of(sysIDStepValue),
                Second.of(sysIDTimeout),
                (state) -> inputs.sysIdState = state.toString()),
            new SysIdRoutine.Mechanism(null, null, this));
  }

  public void periodic() {
    Logger.processInputs(this.getName(), inputs);
    io.updateInputs(inputs);
  }
}


