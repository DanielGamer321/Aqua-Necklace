package com.danielgamer321.rotp_an.client.render.entity.model.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.ConditionalModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.IModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class AquaNecklaceModel extends HumanoidStandModel<AquaNecklaceEntity> {
	private final ModelRenderer cloud;
	private final ModelRenderer water;

	public AquaNecklaceModel() {
		super();

		addHumanoidBaseBoxes(null);
		texWidth = 128;
		texHeight = 128;

		head.texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.1F, false);
		head.texOffs(24, 0).addBox(-4.5F, -4.5F, -1.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

		cloud = new ModelRenderer(this);
		cloud.setPos(1.0F, 0.0F, 4.0F);
		head.addChild(cloud);
		cloud.texOffs(0, 32).addBox(-5.0F, -4.25F, -1.0F, 8.0F, 4.0F, 4.0F, 6.0F, false);

		torso.texOffs(20, 64).addBox(-3.5F, 1.1F, -2.0F, 7.0F, 3.0F, 1.0F, 0.4F, false);
		torso.texOffs(24, 73).addBox(-2.5F, 4.0F, -2.2F, 5.0F, 6.0F, 1.0F, 0.0F, false);

		leftForeArm.texOffs(42, 97).addBox(1.5F, 5.1F, -2.0F, 1.0F, 1.0F, 4.0F, -0.2F, true);

		rightForeArm.texOffs(10, 97).addBox(-2.5F, 5.1F, -2.0F, 1.0F, 1.0F, 4.0F, -0.2F, false);

		leftLeg.texOffs(94, 119).addBox(-0.9F, 4.5F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		rightLeg.texOffs(62, 119).addBox(-1.1F, 4.5F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		water = new ModelRenderer(this);
		water.setPos(0.0F, 18.0F, -1.0F);
		body.addChild(water);
		water.texOffs(0, 40).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
	}

	@Override
	protected RotationAngle[][] initSummonPoseRotations() {
		return new RotationAngle[][] {
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 5F, 15F, 0F),
						RotationAngle.fromDegrees(body, 10F, 20F, -5F),
						RotationAngle.fromDegrees(upperPart, 0F, 0F, 0F),
						RotationAngle.fromDegrees(leftArm, 0F, 30F, -20F),
						RotationAngle.fromDegrees(leftArmJoint, 0F, 0F, 0F),
						RotationAngle.fromDegrees(leftForeArm, -10F, 0F, 0F),
						RotationAngle.fromDegrees(rightArm, -5F, -75F, 10F),
						RotationAngle.fromDegrees(rightArmJoint, 0F, 0F, 15F),
						RotationAngle.fromDegrees(rightForeArm, 0F, 0F, 40F),
						RotationAngle.fromDegrees(leftLeg, -55F, -45F, 0F),
						RotationAngle.fromDegrees(leftLegJoint, 30F, 0F, 0F),
						RotationAngle.fromDegrees(leftLowerLeg, 50F, 0F, 7.5F),
						RotationAngle.fromDegrees(rightLeg, -67F, 15F, 10F),
						RotationAngle.fromDegrees(rightLegJoint, 32.5F, 0F, 0F),
						RotationAngle.fromDegrees(rightLowerLeg, 70F, 0F, 0F)
				},
				new RotationAngle[] {
						RotationAngle.fromDegrees(head, 0F, 0F, 0F),
						RotationAngle.fromDegrees(body, 2.5F, 0F, 0F),
						RotationAngle.fromDegrees(upperPart, 0F, 0F, 0F),
						RotationAngle.fromDegrees(leftArm, -10F, 0F, -110F),
						RotationAngle.fromDegrees(leftArmJoint, 0F, 0F, 45F),
						RotationAngle.fromDegrees(leftForeArm, 0F, 180F, -90F),
						RotationAngle.fromDegrees(rightArm, -10F, 0F, 110F),
						RotationAngle.fromDegrees(rightArmJoint, 0F, 0F, 45F),
						RotationAngle.fromDegrees(rightForeArm, 0F, 180F, 90F),
						RotationAngle.fromDegrees(leftLeg, 0F, 0F, -15F),
						RotationAngle.fromDegrees(leftLegJoint, 0F, 0F, 11F),
						RotationAngle.fromDegrees(leftLowerLeg, 0F, 0F, 30F),
						RotationAngle.fromDegrees(rightLeg, 0F, 0F, -15F),
						RotationAngle.fromDegrees(rightLegJoint, 0F, 0F, 11F),
						RotationAngle.fromDegrees(rightLowerLeg, 0F, 0F, 30F)
				}
		};
	}

	@Override
	protected IModelPose<AquaNecklaceEntity> initBaseIdlePose() {
		return new ConditionalModelPose<AquaNecklaceEntity>()
				.addPose(aqua -> aqua == null || aqua.getState() >= 1,
						new ModelPose<AquaNecklaceEntity>(new RotationAngle[] {
								RotationAngle.fromDegrees(body, 12.5F, 0F, 0F),
								RotationAngle.fromDegrees(upperPart, 0F, 0F, 0F),
								RotationAngle.fromDegrees(leftArm, -24.4704F, 45.1866F, -44.8121F),
								RotationAngle.fromDegrees(leftArmJoint, -17.5F, 0F, 0F),
								RotationAngle.fromDegrees(leftForeArm, -25F, -1F, 0F),
								RotationAngle.fromDegrees(rightArm, -24.4704F, -45.1866F, 44.8121F),
								RotationAngle.fromDegrees(rightArmJoint, -17.5F, 0F, 0F),
								RotationAngle.fromDegrees(rightForeArm, -25F, 1F, 0F),
								RotationAngle.fromDegrees(leftLeg, -20.979F, -25.0193F, -9.3471F),
								RotationAngle.fromDegrees(leftLegJoint, 10F, 0F, 0F),
								RotationAngle.fromDegrees(leftLowerLeg, 16.86410F, 4.3729F, 5.2995F),
								RotationAngle.fromDegrees(rightLeg, -20.979F, 25.0193F, 9.3471F),
								RotationAngle.fromDegrees(rightLegJoint, 10F, 0F, 0F),
								RotationAngle.fromDegrees(rightLowerLeg, 16.8641F, -4.3729F, -5.2995F),
								RotationAngle.fromDegrees(water, -12.5F, 0F, 0F),
						}).setAdditionalAnim(HEAD_ROTATION))
				.addPose(aqua -> aqua != null && aqua.getState() == 0,
						new ModelPose<AquaNecklaceEntity>(new RotationAngle[] {
								RotationAngle.fromDegrees(body, 12.5F, 0F, 0F),
								RotationAngle.fromDegrees(upperPart, 0F, 0F, 0F),
								RotationAngle.fromDegrees(leftArm, -66.3253F, -10.8036F, -59.4013F),
								RotationAngle.fromDegrees(leftArmJoint, -17.5F, 0F, 0F),
								RotationAngle.fromDegrees(leftForeArm, -25F, -1F, 0F),
								RotationAngle.fromDegrees(rightArm, -66.3253F, 10.8036F, 59.4013F),
								RotationAngle.fromDegrees(rightArmJoint, -17.5F, 0F, 0F),
								RotationAngle.fromDegrees(rightForeArm, -25F, 1F, 0F),
								RotationAngle.fromDegrees(leftLeg, -20.979F, -25.0193F, -9.3471F),
								RotationAngle.fromDegrees(leftLegJoint, 10F, 0F, 0F),
								RotationAngle.fromDegrees(leftLowerLeg, 16.86410F, 4.3729F, 5.2995F),
								RotationAngle.fromDegrees(rightLeg, -20.979F, 25.0193F, 9.3471F),
								RotationAngle.fromDegrees(rightLegJoint, 10F, 0F, 0F),
								RotationAngle.fromDegrees(rightLowerLeg, 16.8641F, -4.3729F, -5.2995F),
						}).setAdditionalAnim(HEAD_ROTATION));
	}

	@Override
	protected IModelPose<AquaNecklaceEntity> initIdlePose2Loop() {
		return new ConditionalModelPose<AquaNecklaceEntity>()
				.addPose(aqua -> aqua == null || aqua.getState() >= 1,
						new ModelPose<>(new RotationAngle[] {
								RotationAngle.fromDegrees(leftArm, -23.9215F, 44.8097F, -46.8648F),
								RotationAngle.fromDegrees(leftArmJoint, -15F, 0F, 0F),
								RotationAngle.fromDegrees(leftForeArm, -29.0129F, -1.396F, 3.0421F),
								RotationAngle.fromDegrees(rightArm, -23.9215F, -44.8097F, 46.8648F),
								RotationAngle.fromDegrees(rightArmJoint, -15F, 0F, 0F),
								RotationAngle.fromDegrees(rightForeArm, -29.0129F, 1.396F, -3.0421F)
						}))
				.addPose(aqua -> aqua != null && aqua.getState() == 0,
						new ModelPose<>(new RotationAngle[] {
								RotationAngle.fromDegrees(leftArm, -70.2267F, -2.7704F, -60.2443F),
								RotationAngle.fromDegrees(leftForeArm, -31.4382F, 1.0602F, 3.248F),
								RotationAngle.fromDegrees(rightArm, -70.2267F, 2.7704F, 60.2443F),
								RotationAngle.fromDegrees(rightForeArm, -31.4382F, -1.0602F, -3.248F)
						})
				);
	}
}