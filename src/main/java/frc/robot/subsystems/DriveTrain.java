// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;




import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.LogitechExtreme3DProController;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
    public  CANSparkMax topLeft = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax mainBotLeft = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax topRight = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax mainBotRight = new CANSparkMax(4, MotorType.kBrushless);



    private LogitechExtreme3DProController controller = new LogitechExtreme3DProController(2);



    DifferentialDrive dt = new DifferentialDrive(mainBotLeft::set, mainBotRight::set);
  public DriveTrain() {
    
    topLeft.follow(mainBotLeft);
    topRight.follow(mainBotRight);

    mainBotRight.setInverted(true);

    


  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command driveCommand(DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          dt.arcadeDrive(-xSpeed.getAsDouble(), -zRotation.getAsDouble());
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void setCoast(){
    mainBotLeft.setIdleMode(IdleMode.kCoast);
    mainBotRight.setIdleMode(IdleMode.kCoast);
    topLeft.setIdleMode(IdleMode.kCoast);
    topRight.setIdleMode(IdleMode.kCoast);
  }
  public void setBrake(){
    mainBotLeft.setIdleMode(IdleMode.kBrake);
    mainBotRight.setIdleMode(IdleMode.kBrake);
    topLeft.setIdleMode(IdleMode.kBrake);
    topRight.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
