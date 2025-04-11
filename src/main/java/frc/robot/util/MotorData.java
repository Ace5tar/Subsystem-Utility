package frc.robot.util;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class MotorData {

  private int m_id;
  private TalonFXConfiguration m_config;

  /**
   * Creates a new set of MotorData using the ID given.
   *
   * @param motorID ID of the motor.
   */
  public MotorData(int motorID) {
    m_id = motorID;
    m_config = new TalonFXConfiguration();
  }

  /**
   * Sets the stator current limit value in the motor config to the given value. Automatically
   * enables the use of stator current limit for the motor.
   *
   * @param currentLimit Current limit to set to.
   */
  public void setCurrentLimit(double currentLimit) {
    m_config.CurrentLimits.StatorCurrentLimit = currentLimit;
    m_config.CurrentLimits.StatorCurrentLimitEnable = true;
  }

  /**
   * Sets the stator current limit value in the motor config to the given value. Automatically
   * enables the use of stator current limit for the motor.
   *
   * <p>Same as {@code setCurrentLimit} but named differently for better readability when chaining
   * methods.
   *
   * @param currentLimit Current limit to set to.
   * @return This object for method chaining.
   */
  public MotorData withCurrentLimit(double currentLimit) {
    m_config.CurrentLimits.StatorCurrentLimit = currentLimit;
    m_config.CurrentLimits.StatorCurrentLimitEnable = true;

    return this;
  }

  /**
   * Sets the {@link InvertedValue} for the motor config.
   *
   * @param isInverted :
   *     <p>{@code false} = {@code Clockwise_Positive}
   *     <p>{@code true} = {@code CounterClockwise_Positive }
   */
  public void setInverted(boolean isInverted) {
    m_config.MotorOutput.Inverted =
        isInverted // TODO find the default inverted value, currently assumed to be Clockwise
            ? InvertedValue.CounterClockwise_Positive
            : InvertedValue.Clockwise_Positive;
  }

  /**
   * Sets the {@link InvertedValue} for the motor config.
   *
   * <p>Same as {@code setInverted} but named differently for better readability when chaining
   * methods.
   *
   * @param isInverted :
   *     <p>{@code false} = {@code Clockwise_Positive}
   *     <p>{@code true} = {@code CounterClockwise_Positive}
   * @return This object for method chaining.
   */
  public MotorData withInverted(boolean isInverted) {
    m_config.MotorOutput.Inverted =
        isInverted // TODO find the default inverted value, currently assumed to be Clockwise
            ? InvertedValue.CounterClockwise_Positive
            : InvertedValue.Clockwise_Positive;

    return this;
  }

  /**
   * Sets the PID values for the motor config at slot 0.
   *
   * @param kP The PID proportional constant.
   * @param kI The PID integral constant.
   * @param kD The PID derivative constant.
   */
  public void setPID(double kP, double kI, double kD) {
    m_config.Slot0.kP = kP;
    m_config.Slot0.kI = kI;
    m_config.Slot0.kD = kD;
  }

  /**
   * Sets the PID values for the motor config at slot 0.
   *
   * <p>Same as {@code setPID} but named differently for better readability when chaining methods.
   *
   * @param kP The PID proportional constant.
   * @param kI The PID integral constant.
   * @param kD The PID derivative constant.
   * @return This object for method chaining.
   */
  public MotorData withPID(double kP, double kI, double kD) {
    m_config.Slot0.kP = kP;
    m_config.Slot0.kI = kI;
    m_config.Slot0.kD = kD;

    return this;
  }

  /**
   * Sets the Feed Forward values for the motor config at slot 0.
   *
   * @param kG The FF gravity constant.
   * @param kS The FF static friction constant.
   * @param kV The FF velocity constant.
   * @param kA The FF acceleration constant.
   */
  public void setFF(double kG, double kS, double kV, double kA) {
    m_config.Slot0.kG = kG;
    m_config.Slot0.kS = kS;
    m_config.Slot0.kV = kV;
    m_config.Slot0.kA = kA;
  }

  /**
   * Sets the Feed Forward values for the motor config at slot 0.
   *
   * <p>Same as {@code setFF} but named differently for better readability when chaining methods.
   *
   * @param kG The FF gravity constant.
   * @param kS The FF static friction constant.
   * @param kV The FF velocity constant.
   * @param kA The FF acceleration constant.
   * @return This object for method chaining.
   */
  public MotorData withFF(double kG, double kS, double kV, double kA) {
    m_config.Slot0.kG = kG;
    m_config.Slot0.kS = kS;
    m_config.Slot0.kV = kV;
    m_config.Slot0.kA = kA;

    return this;
  }

  /**
   * Sets the max speeds with motion magic for the motor config. Only uses {@code
   * MotionMagicCruiseVelocity} and {@code MotionMagicAcceleration}.
   *
   * @param maxVelocity Maximum velocity motion magic will attempt to reach.
   * @param maxAcceleration Maximum acceleration motion magic will attempt to reach.
   */
  public void setMaxSpeeds(double maxVelocity, double maxAcceleration) {
    m_config.MotionMagic.MotionMagicCruiseVelocity = maxVelocity;
    m_config.MotionMagic.MotionMagicAcceleration = maxAcceleration;
  }

  /**
   * Sets the max speeds with motion magic for the motor config. Only uses {@code
   * MotionMagicCruiseVelocity} and {@code MotionMagicAcceleration}.
   *
   * <p>Same as {@code setMaxSpeeds} but named differently for better readability when chaining
   * methods.
   *
   * @param maxVelocity Maximum velocity motion magic will attempt to reach.
   * @param maxAcceleration Maximum acceleration motion magic will attempt to reach.
   * @return This object for method chaining.
   */
  public MotorData withMaxSpeeds(double maxVelocity, double maxAcceleration) {
    m_config.MotionMagic.MotionMagicCruiseVelocity = maxVelocity;
    m_config.MotionMagic.MotionMagicAcceleration = maxAcceleration;

    return this;
  }

  /**
   * Sets the software limit switch for the motor configuration. The motor will set the applied
   * voltage to zero if the motor attemps to extend past the soft stops.
   *
   * @param maxPosition Maximum position in rotations the motor can reach.
   * @param minPosition Minumum position in rotations the motor can reach.
   */
  public void setSoftStops(double maxPosition, double minPosition) {
    m_config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    m_config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = maxPosition;

    m_config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    m_config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = minPosition;
  }

  /**
   * Sets the software limit switch for the motor configuration. The motor will set the applied
   * voltage to zero if the motor attemps to extend past the soft stops.
   *
   * <p>Same as {@code setSoftStops} but named differently for better readability when chaining
   * methods.
   *
   * @param maxPosition Maximum position in rotations the motor can reach.
   * @param minPosition Minumum position in rotations the motor can reach.
   * @return This object for method chaining.
   */
  public MotorData withSoftStops(double maxPosition, double minPosition) {
    m_config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    m_config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = maxPosition;

    m_config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    m_config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = minPosition;

    return this;
  }

  /**
   * Sets the ratio of the mechanism to the motor. This method is intended to be used when using
   * another encoder that is 1:1 with the mechanisms movement.
   *
   * @param gearRatio Ratio of motor rotations to mechanism movements.
   */
  public void setExternalEncoderRatios(double gearRatio) {
    m_config.Feedback.SensorToMechanismRatio = 1;
    m_config.Feedback.RotorToSensorRatio = gearRatio;
  }

  /**
   * Sets the ratio of the mechanism to the motor. This method is intended to be used when using
   * another encoder that is 1:1 with the mechanisms movement.
   *
   * <p>Same as {@code setExternalEncoderRatios} but named differently for better readability when
   * chaining methods.
   *
   * @param gearRatio Ratio of motor rotations to mechanism movements.
   * @return This object for method chaining.
   */
  public MotorData withExternalEncoderRatios(double gearRatio) {
    m_config.Feedback.SensorToMechanismRatio = 1;
    m_config.Feedback.RotorToSensorRatio = gearRatio;

    return this;
  }

  /**
   * Sets the ratio of the mechanism to the motor. This method is intended to be used when using the
   * internal motor encoder.
   *
   * @param gearRatio Ratio of motor rotations to mechanism movements.
   */
  public void setInternalEncoderRatios(double gearRatio) {
    m_config.Feedback.SensorToMechanismRatio = gearRatio;
    m_config.Feedback.RotorToSensorRatio = 1;
  }

  /**
   * Sets the ratio of the mechanism to the motor. This method is intended to be used when using the
   * internal motor encoder.
   *
   * <p>Same as {@code setInternalEncoderRatios} but named differently for better readability when
   * chaining methods.
   *
   * @param gearRatio Ratio of motor rotations to mechanism movements.
   * @return This object for method chaining.
   */
  public MotorData withInternalEncoderRatios(double gearRatio) {
    m_config.Feedback.SensorToMechanismRatio = gearRatio;
    m_config.Feedback.RotorToSensorRatio = 1;

    return this;
  }

  /**
   * Sets whether to put the motor in brake mode when no voltage is applied.
   *
   * @param isBrakeMode
   *     <p>{@code false} = {@code NeutralModeValue.Coast}
   *     <p>{@code true} = {@code NeutralModeValue.Brake}
   */
  public void setBrakeMode(boolean isBrakeMode) {
    m_config.MotorOutput.NeutralMode =
        isBrakeMode ? NeutralModeValue.Coast : NeutralModeValue.Brake;
  }

  /**
   * Sets whether to put the motor in brake mode when no voltage is applied.
   *
   * <p>Same as {@code setBrakeMode} but named differently for better readability when chaining
   * methods.
   *
   * @param isBrakeMode
   *     <p>{@code false} = {@code NeutralModeValue.Coast}
   *     <p>{@code true} = {@code NeutralModeValue.Brake}
   * @return This object for method chaining.
   */
  public MotorData withBrakeMode(boolean isBrakeMode) {
    m_config.MotorOutput.NeutralMode =
        isBrakeMode ? NeutralModeValue.Coast : NeutralModeValue.Brake;

    return this;
  }

  /**
   * Sets the motor config to the given motor config. Useful for when multiple motors on one
   * subsystem use very similar configurations.
   *
   * @param otherConfig Motor config to copy.
   */
  public void copyMotorConfig(TalonFXConfiguration otherConfig) {
    m_config = otherConfig;
  }

  /**
   * Useful to supply a motor config for another motor to copy.
   *
   * @return The motor configuration of this MotorData.
   */
  public TalonFXConfiguration getMotorConfig() {
    return m_config;
  }

  /**
   * @return The ID of this MotorData.
   */
  public int getMotorID() {
    return m_id;
  }
}
